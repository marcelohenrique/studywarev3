/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.controleestudo.managedbean;

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

import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;
import br.com.guarasoft.conteudoprogramatico.concurso.persistence.ConcursoRepository;
import br.com.guarasoft.conteudoprogramatico.concursomateria.entidade.ConcursoMateria;
import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateriaRepository;
import br.com.guarasoft.conteudoprogramatico.concursomateriaestudada.ConcursoMateriaEstudada;
import br.com.guarasoft.conteudoprogramatico.concursomateriaestudada.ConcursoMateriaEstudadaRepository;
import br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence.EstudoSemanal;
import br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence.EstudoSemanalRepository;
import br.com.guarasoft.conteudoprogramatico.materia.persistence.Materia;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.entidade.MateriaEstudada;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence.MateriaEstudadaDao;

/**
 * @author guara
 * 
 */
@Data
@ManagedBean(name = "controleestudo")
@ViewScoped
public class ControleEstudoMBean implements Serializable {

	private final Logger logger = LoggerFactory.getLogger(ControleEstudoMBean.class);

	@Resource
	private UserTransaction userTransaction;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5358580904420656733L;

	@Inject
	private MateriaEstudadaDao materiaEstudadaDao;
	@Inject
	private ConcursoRepository concursoRepository;
	@Inject
	private ConcursoMateriaRepository concursoMateriaRepository;
	@Inject
	private EstudoSemanalRepository estudosSemanaisRepository;
	@Inject
	private ConcursoMateriaEstudadaRepository concursoMateriaEstudadaRepository;

	public boolean btIniciarDisabled = false;
	public boolean btZerarDisabled = true;
	public boolean btGravarDisabled = true;

	private Long horasEstudadaInMillis;
	private Duration tempoTotalAlocado = new Duration(0);
	private Duration tempoEstudadoTotal = new Duration(0);

	@Inject
	private Concurso concursoSelecionado;
	private List<ConcursoMateriaEstudada> concursoMateriasEstudadas;
	private ConcursoMateria concursoMateriaSelecionada;
	private MateriaEstudada materiaEstudada;
	private List<MateriaEstudada> materiasEstudadas;
	private List<EstudoSemanal> estudosSemanais;
	private List<ConcursoMateria> concursoMaterias;

	@PostConstruct
	private void init() {
		materiaEstudada = build();
	}
	
	private void atualiza() {
		concursoMateriasEstudadas = concursoMateriaEstudadaRepository.findAll(concursoSelecionado);
		for (ConcursoMateriaEstudada concursoMateria : concursoMateriasEstudadas) {
			tempoTotalAlocado = tempoTotalAlocado.plus(concursoMateria.getConcursoMateria().getTempoAlocado());
			tempoEstudadoTotal = tempoEstudadoTotal.plus(concursoMateria.getSomaTempo());
		}
		materiasEstudadas = materiaEstudadaDao.findAll(concursoSelecionado);
		estudosSemanais = estudosSemanaisRepository.findAll(concursoSelecionado);
	}

	private MateriaEstudada build() {
		MateriaEstudada materiaEstudada = new MateriaEstudada();
		ConcursoMateria concursoMateria = new ConcursoMateria();
		concursoMateria.setMateria(new Materia());
		materiaEstudada.setConcursoMateria(concursoMateria);
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

		materiaEstudada.setConcursoMateria(concursoMateriaSelecionada);
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
		materiasEstudadas = materiaEstudadaDao.findAll(concursoSelecionado);
		materiaEstudada = build();
		atualiza();
	}

	public boolean getBtPausarDisabled() {
		return !btIniciarDisabled;
	}

	public void listaMateriasConcurso() {
		concursoMaterias = concursoMateriaRepository
				.findAll(concursoSelecionado);
		atualiza();
	}
}
