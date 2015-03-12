package br.com.guarasoft.studyware.controleestudo.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.guarasoft.studyware.controleestudo.presenter.GraficoDiario;
import br.com.guarasoft.studyware.estudodiario.bean.EstudoDiarioBean;
import br.com.guarasoft.studyware.estudodiario.gateway.EstudoDiaGateway;
import br.com.guarasoft.studyware.estudosemanal.bean.EstudoSemanalBean;
import br.com.guarasoft.studyware.estudosemanal.gateway.EstudoSemanalGateway;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.UsuarioEstudoGateway;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.UsuarioEstudoMateriaGateway;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.ResumoMateriaEstudadaBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.UsuarioEstudoMateriaHistoricoGateway;

@ManagedBean(name = "controleestudo")
@ViewScoped
@Data
public class ControleEstudoController implements Serializable {

	private static final long serialVersionUID = -5358580904420656733L;

	private final Logger logger = LoggerFactory.getLogger(ControleEstudoController.class);

	@Inject
	private UsuarioEstudoGateway usuarioEstudoGateway;
	@Inject
	private UsuarioEstudoMateriaGateway usuarioEstudoMateriaGateway;
	@Inject
	private UsuarioEstudoMateriaHistoricoGateway usuarioEstudoMateriaHistoricoGateway;
	@Inject
	private EstudoSemanalGateway estudoSemanalGateway;
	@Inject
	private EstudoDiaGateway estudoDiaGateway;

	public boolean btIniciarDisabled = false;
	public boolean btZerarDisabled = true;
	public boolean btGravarDisabled = true;

	private Long horasEstudadaInMillis;
	private Long agoraInMillis;
	private Duration tempoTotalAlocado = new Duration(0);
	private Duration tempoEstudadoTotal = new Duration(0);

	@Inject
	private UsuarioEstudoBean estudoSelecionado;

	private List<ResumoMateriaEstudadaBean> resumoMateriasEstudadas;
	private UsuarioEstudoMateriaBean estudoMateriaSelecionada;
	private UsuarioEstudoMateriaHistoricoBean materiaEstudada;
	private List<UsuarioEstudoMateriaHistoricoBean> materiasEstudadas;
	private List<EstudoSemanalBean> estudosSemanais;
	private List<UsuarioEstudoMateriaBean> usuarioEstudoMaterias;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	private Usuario usuario;

	private List<UsuarioEstudoBean> estudos;
	
	@Setter(AccessLevel.PRIVATE)
	private GraficoDiario graficoDiario;

	@PostConstruct
	private void init() {
		this.materiaEstudada = this.build();

		this.estudos = this.usuarioEstudoGateway.recuperaEstudosValidos(this.usuario.getEmail());
	}

	private void atualiza() {
		this.resumoMateriasEstudadas = this.usuarioEstudoMateriaHistoricoGateway.buscaResumosMaterias(this.estudoSelecionado);
		this.tempoTotalAlocado = new Duration(0);
		this.tempoEstudadoTotal = new Duration(0);
		for (ResumoMateriaEstudadaBean resumoMateriaEstudada : this.resumoMateriasEstudadas) {
			this.tempoTotalAlocado = this.tempoTotalAlocado.plus(resumoMateriaEstudada.getUsuarioEstudoMateria().getTempoAlocado());
			this.tempoEstudadoTotal = this.tempoEstudadoTotal.plus(resumoMateriaEstudada.getSomaTempo());
		}
		this.materiasEstudadas = this.usuarioEstudoMateriaHistoricoGateway.findAll(this.estudoSelecionado);
		this.estudosSemanais = this.estudoSemanalGateway.findAll(this.estudoSelecionado);

		Collection<EstudoDiarioBean> estudosDiarios = this.estudoDiaGateway.findAll(this.estudoSelecionado);

		graficoDiario = new GraficoDiario(estudosDiarios);
	}

	private UsuarioEstudoMateriaHistoricoBean build() {
		UsuarioEstudoMateriaBean usuarioEstudoMateriaBean = new UsuarioEstudoMateriaBean();
		usuarioEstudoMateriaBean.setMateria(new MateriaBean());

		UsuarioEstudoMateriaHistoricoBean materiaEstudada = new UsuarioEstudoMateriaHistoricoBean();
		materiaEstudada.setUsuarioEstudoMateria(usuarioEstudoMateriaBean);
		return materiaEstudada;
	}

	public void iniciar() {
		this.btIniciarDisabled = true;
		this.btZerarDisabled = false;
		this.btGravarDisabled = false;
	}

	public void pausar() {
		this.btIniciarDisabled = false;
	}

	public void zerar() {
		this.btIniciarDisabled = false;
		this.btZerarDisabled = true;
		this.btGravarDisabled = true;

		this.horasEstudadaInMillis = null;
		this.materiaEstudada = this.build();
	}

	public void gravar() {
		this.btIniciarDisabled = false;
		this.btZerarDisabled = true;
		this.btGravarDisabled = true;

		this.materiaEstudada.setUsuarioEstudoMateria(this.estudoMateriaSelecionada);
		this.materiaEstudada.setHoraEstudo(new Date());
		this.materiaEstudada.setTempoEstudado(new Duration(this.horasEstudadaInMillis));

		this.logger.info(this.materiaEstudada.toString());
		this.usuarioEstudoMateriaHistoricoGateway.persist(this.materiaEstudada);
		this.materiasEstudadas = this.usuarioEstudoMateriaHistoricoGateway.findAll(this.estudoSelecionado);
		this.materiaEstudada = this.build();
		this.atualiza();
	}

	public boolean getBtPausarDisabled() {
		return !this.btIniciarDisabled;
	}

	public void listaEstudoMaterias() {
		this.usuarioEstudoMaterias = this.usuarioEstudoMateriaGateway.buscaPorUsuarioEstudo(this.estudoSelecionado);
		this.atualiza();
	}
}
