/**
 * 
 */
package br.com.guarasoft.studyware.controleestudo.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.guarasoft.studyware.estudo.dao.EstudoDao;
import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.estudomateria.dao.EstudoMateriaDao;
import br.com.guarasoft.studyware.estudomateria.entidade.EstudoMateria;
import br.com.guarasoft.studyware.estudosemanal.dao.EstudoSemanalDao;
import br.com.guarasoft.studyware.estudosemanal.entidade.EstudoSemanal;
import br.com.guarasoft.studyware.materia.entidade.Materia;
import br.com.guarasoft.studyware.materiaestudada.dao.MateriaEstudadaDao;
import br.com.guarasoft.studyware.materiaestudada.entidade.MateriaEstudada;
import br.com.guarasoft.studyware.resumomateriaestudada.dao.ResumoMateriaEstudadaDao;
import br.com.guarasoft.studyware.resumomateriaestudada.entidade.ResumoMateriaEstudada;

/**
 * @author guara
 * 
 */
@Data
@ManagedBean(name = "controleestudo")
@ViewScoped
public class ControleEstudoMBean implements Serializable {

	private final Logger logger = LoggerFactory
			.getLogger(ControleEstudoMBean.class);

	@Resource
	private UserTransaction userTransaction;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5358580904420656733L;

	@Inject
	private EstudoDao estudoDao;
	@Inject
	private MateriaEstudadaDao materiaEstudadaDao;
	@Inject
	private EstudoMateriaDao estudoMateriaDao;
	@Inject
	private EstudoSemanalDao estudoSemanalDao;
	@Inject
	private ResumoMateriaEstudadaDao resumoMateriaEstudadaDao;

	public boolean btIniciarDisabled = false;
	public boolean btZerarDisabled = true;
	public boolean btGravarDisabled = true;

	private Long horasEstudadaInMillis;
	private Duration tempoTotalAlocado = new Duration(0);
	private Duration tempoEstudadoTotal = new Duration(0);

	@Inject
	private Estudo estudoSelecionado;

	private List<ResumoMateriaEstudada> concursoMateriasEstudadas;
	private EstudoMateria estudoMateriaSelecionada;
	private MateriaEstudada materiaEstudada;
	private List<MateriaEstudada> materiasEstudadas;
	private List<EstudoSemanal> estudosSemanais;
	private List<EstudoMateria> estudoMaterias;

	@PostConstruct
	private void init() {
		// estudos = estudoDao.findAll();

		materiaEstudada = build();
	}

	private void atualiza() {
		concursoMateriasEstudadas = resumoMateriaEstudadaDao
				.findAll(estudoSelecionado);
		tempoTotalAlocado = new Duration(0);
		tempoEstudadoTotal = new Duration(0);
		for (ResumoMateriaEstudada resumoMateriaEstudada : concursoMateriasEstudadas) {
			tempoTotalAlocado = tempoTotalAlocado.plus(resumoMateriaEstudada
					.getEstudoMateria().getTempoAlocado());
			tempoEstudadoTotal = tempoEstudadoTotal.plus(resumoMateriaEstudada
					.getSomaTempo());
		}
		materiasEstudadas = materiaEstudadaDao.findAll(estudoSelecionado);
		estudosSemanais = estudoSemanalDao.findAll(estudoSelecionado);
	}

	private MateriaEstudada build() {
		MateriaEstudada materiaEstudada = new MateriaEstudada();
		EstudoMateria estudoMateria = new EstudoMateria();
		estudoMateria.setMateria(new Materia());
		materiaEstudada.setEstudoMateria(estudoMateria);
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

		materiaEstudada.setEstudoMateria(estudoMateriaSelecionada);
		materiaEstudada.setDataHoraEstudo(new Date());
		materiaEstudada.setTempoEstudado(new Duration(horasEstudadaInMillis));

		try {
			userTransaction.begin();
			logger.info(materiaEstudada.toString());
			materiaEstudadaDao.persist(materiaEstudada);
			;
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
		estudoMaterias = estudoMateriaDao.findAll(estudoSelecionado);
		atualiza();
	}
}
