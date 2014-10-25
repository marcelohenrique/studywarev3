package br.com.guarasoft.studyware.controleestudo.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import lombok.Data;

import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.guarasoft.studyware.estudodiario.dao.EstudoDiaDao;
import br.com.guarasoft.studyware.estudodiario.entidade.EstudoDiario;
import br.com.guarasoft.studyware.estudosemanal.dao.EstudoSemanalDao;
import br.com.guarasoft.studyware.estudosemanal.entidade.EstudoSemanal;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.resumomateriaestudada.bean.ResumoMateriaEstudadaBean;
import br.com.guarasoft.studyware.resumomateriaestudada.gateway.ResumoMateriaEstudadaDao;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.UsuarioEstudoGateway;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.UsuarioEstudoMateriaGateway;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.MateriaEstudadaDao;

@ManagedBean(name = "controleestudo")
@ViewScoped
@Data
public class ControleEstudoMBean implements Serializable {

	private static final int SEGUNDO = 1000;
	private static final int MINUTO = SEGUNDO * 60;
	private static final int HORA = MINUTO * 60;

	private static final long serialVersionUID = -5358580904420656733L;

	private final Logger logger = LoggerFactory
			.getLogger(ControleEstudoMBean.class);

	@Resource
	private UserTransaction userTransaction;

	@Inject
	private MateriaEstudadaDao materiaEstudadaDao;
	@Inject
	private UsuarioEstudoMateriaGateway usuarioEstudoMateriaGateway;
	@Inject
	private EstudoSemanalDao estudoSemanalDao;
	@Inject
	private ResumoMateriaEstudadaDao resumoMateriaEstudadaDao;
	@Inject
	private EstudoDiaDao estudoDiaDao;

	public boolean btIniciarDisabled = false;
	public boolean btZerarDisabled = true;
	public boolean btGravarDisabled = true;

	private Long horasEstudadaInMillis;
	private Duration tempoTotalAlocado = new Duration(0);
	private Duration tempoEstudadoTotal = new Duration(0);

	@Inject
	private UsuarioEstudoBean estudoSelecionado;

	private List<ResumoMateriaEstudadaBean> resumoMateriasEstudadas;
	private UsuarioEstudoMateriaBean estudoMateriaSelecionada;
	private UsuarioEstudoMateriaHistoricoBean materiaEstudada;
	private List<UsuarioEstudoMateriaHistoricoBean> materiasEstudadas;
	private List<EstudoSemanal> estudosSemanais;
	private List<UsuarioEstudoMateriaBean> usuarioEstudoMaterias;
	private List<EstudoDiario> estudosDiarios;

	private LineChartModel graficoEstudoDiario;

	@Inject
	private UsuarioEstudoGateway usuarioEstudoGateway;
	@ManagedProperty(value = "#{sessionAuth.usuario}")
	private UsuarioService usuarioService;
	private List<UsuarioEstudoBean> estudos;

	@PostConstruct
	private void init() {
		materiaEstudada = build();

		this.estudos = this.usuarioEstudoGateway.recuperaEstudos(usuarioService
				.getEmail());
	}

	private void atualiza() {
		resumoMateriasEstudadas = resumoMateriaEstudadaDao
				.findAll(estudoSelecionado);
		tempoTotalAlocado = new Duration(0);
		tempoEstudadoTotal = new Duration(0);
		for (ResumoMateriaEstudadaBean resumoMateriaEstudada : resumoMateriasEstudadas) {
			tempoTotalAlocado = tempoTotalAlocado.plus(resumoMateriaEstudada
					.getUsuarioEstudoMateria().getTempoAlocado());
			tempoEstudadoTotal = tempoEstudadoTotal.plus(resumoMateriaEstudada
					.getSomaTempo());
		}
		materiasEstudadas = materiaEstudadaDao.findAll(estudoSelecionado);
		estudosSemanais = estudoSemanalDao.findAll(estudoSelecionado);

		estudosDiarios = estudoDiaDao.findAll(estudoSelecionado);

		this.graficoEstudoDiario = new LineChartModel();
		this.graficoEstudoDiario.setLegendPosition("e");

		Axis eixoY = this.graficoEstudoDiario.getAxis(AxisType.Y);
		eixoY.setMin(0);
		eixoY.setMax(24);

		DateAxis axis = new DateAxis("Dates");
		axis.setTickAngle(-50);
		// axis.setMax("2014-02-01");
		axis.setTickFormat("%b %#d, %y");

		this.graficoEstudoDiario.getAxes().put(AxisType.X, axis);

		LineChartSeries planejado = new LineChartSeries();
		planejado.setLabel("Planejado");
		this.graficoEstudoDiario.addSeries(planejado);

		LineChartSeries executado = new LineChartSeries();
		executado.setLabel("Executado");
		this.graficoEstudoDiario.addSeries(executado);

		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
		for (EstudoDiario estudoDiario : this.estudosDiarios) {
			if (estudoDiario != null) {
				String date = formatter.print(estudoDiario
						.getDataInicioSemana().getTime());
				Long tempoAlocado = estudoDiario.getTempoAlocado().getMillis()
						/ HORA;
				Long tempoEstudado = estudoDiario.getTempoEstudado()
						.getMillis() / HORA;
				planejado.set(date, tempoAlocado);
				executado.set(date, tempoEstudado);
			}
		}
	}

	private UsuarioEstudoMateriaHistoricoBean build() {
		UsuarioEstudoMateriaBean usuarioEstudoMateriaBean = new UsuarioEstudoMateriaBean();
		usuarioEstudoMateriaBean.setMateriaBean(new MateriaBean());

		UsuarioEstudoMateriaHistoricoBean materiaEstudada = new UsuarioEstudoMateriaHistoricoBean();
		materiaEstudada.setUsuarioEstudoMateria(usuarioEstudoMateriaBean);
		return materiaEstudada;
	}

	public void iniciar() {
		btIniciarDisabled = true;
		btZerarDisabled = false;
		btGravarDisabled = false;
	}

	public void pausar() {
		btIniciarDisabled = false;
	}

	public void zerar() {
		btIniciarDisabled = false;
		btZerarDisabled = true;
		btGravarDisabled = true;

		horasEstudadaInMillis = null;
		materiaEstudada = build();
	}

	public void gravar() {
		btIniciarDisabled = false;
		btZerarDisabled = true;
		btGravarDisabled = true;

		materiaEstudada.setUsuarioEstudoMateria(estudoMateriaSelecionada);
		materiaEstudada.setHoraEstudo(new Date());
		materiaEstudada.setTempoEstudado(new Duration(horasEstudadaInMillis));

		try {
			userTransaction.begin();
			logger.info(materiaEstudada.toString());
			materiaEstudadaDao.persist(materiaEstudada);
			userTransaction.commit();
		} catch (SecurityException | IllegalStateException
				| NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
		materiasEstudadas = materiaEstudadaDao.findAll(estudoSelecionado);
		materiaEstudada = build();
		atualiza();
	}

	public boolean getBtPausarDisabled() {
		return !btIniciarDisabled;
	}

	public void listaEstudoMaterias() {
		usuarioEstudoMaterias = usuarioEstudoMateriaGateway.findAll(estudoSelecionado);
		atualiza();
	}
}
