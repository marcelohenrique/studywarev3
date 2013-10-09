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

import br.com.guarasoft.conteudoprogramatico.concurso.persistence.Concurso;
import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateria;
import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateriaEstudada;
import br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence.EstudoSemanal;
import br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence.EstudoSemanalRepository;
import br.com.guarasoft.conteudoprogramatico.materia.persistence.Materia;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence.MateriaEstudada;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence.MateriaEstudadaImpl;

/**
 * @author guara
 * 
 */
@Data
@ManagedBean(name = "controleestudo")
@ViewScoped
public class ControleEstudoMBean implements Serializable {

	@Resource
	private UserTransaction userTransaction;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5358580904420656733L;

	public boolean btIniciarDisabled = false;
	public boolean btZerarDisabled = true;
	public boolean btGravarDisabled = true;

	private Long horasEstudadaInMillis;

	@ManagedProperty("#{materiastable}")
	private MateriasTableMBean materiasTableMBean;

	private List<MateriaEstudada> materiasEstudadas;
	private List<EstudoSemanal> estudosSemanais;
	@Inject
	private MateriaEstudada materiaEstudada;
	@Inject
	private EstudoSemanalRepository estudosSemanaisRepository;

	private Integer codigoMateriaEstudada;

	// private MateriaEstudadaImpl materiaEstudadaImpl;

	private MateriaEstudada materiaEstudadaSelecionada;

	@Inject
	private Concurso concursoRepository;

	private List<Concurso> concursoRepositories;

	@PostConstruct
	private void init() {
		concursoRepositories = concursoRepository.findAll();

		materiasEstudadas = materiaEstudada.findAll();
		estudosSemanais = estudosSemanaisRepository.findAll();
		materiaEstudada = build();
	}

	private MateriaEstudada build() {
		MateriaEstudada materiaEstudada = new MateriaEstudadaImpl();
		ConcursoMateria concursoMateria = new ConcursoMateria();
		Materia materia = new Materia();
		concursoMateria.setMateria(materia);
		materiaEstudada.getMateriaEstudadaPK().setConcursoMateria(concursoMateria);
		return materiaEstudada;
	}

	public void iniciar() {
		btIniciarDisabled = true;
		btZerarDisabled = false;
		btGravarDisabled = false;

		for (ConcursoMateriaEstudada concursoMateria : materiasTableMBean
				.getConcursoMaterias()) {
			if (concursoMateria.getConcursoMateria().getMateria().getCodigo()
					.equals(codigoMateriaEstudada)) {
				materiaEstudada.getMateriaEstudadaPK().setConcursoMateria(concursoMateria
						.getConcursoMateria());
				break;
			}
		}
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

		materiaEstudada.getMateriaEstudadaPK().setDataHoraEstudo(new Date());
		materiaEstudada.setTempoEstudado(new Duration(horasEstudadaInMillis));

		try {
			userTransaction.begin();
			materiaEstudada.saveOrUpdate();
			userTransaction.commit();
		} catch (SecurityException | IllegalStateException
				| NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
		materiasEstudadas = materiaEstudada.findAll();
		materiaEstudada = build();
	}

	public boolean getBtPausarDisabled() {
		return !btIniciarDisabled;
	}

	/*
	 * public void onCellEdit(CellEditEvent event) { Object oldValue =
	 * event.getOldValue(); Object newValue = event.getNewValue();
	 * 
	 * if (newValue != null && !newValue.equals(oldValue)) { FacesMessage msg =
	 * new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " +
	 * oldValue + ", New:" + newValue);
	 * FacesContext.getCurrentInstance().addMessage(null, msg); } }
	 */
}
