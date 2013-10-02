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
import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateria;
import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateriaEstudada;
import br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence.EstudoSemanal;
import br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence.EstudoSemanalRepository;
import br.com.guarasoft.conteudoprogramatico.materia.persistence.Materia;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence.MateriaEstudada;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence.MateriaEstudadaRepository;

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
	private MateriaEstudadaRepository materiaEstudadaRepository;
	@Inject
	private EstudoSemanalRepository estudosSemanaisRepository;

	private Integer codigoMateriaEstudada;

	private MateriaEstudada materiaEstudada;

	@PostConstruct
	private void init() {
		materiasEstudadas = materiaEstudadaRepository.findAll();
		estudosSemanais = estudosSemanaisRepository.findAll();
		materiaEstudada = build();
	}

	private MateriaEstudada build() {
		MateriaEstudada materiaEstudada = new MateriaEstudada();
		ConcursoMateria concursoMateria = new ConcursoMateria();
		Materia materia = new Materia();
		concursoMateria.setMateria(materia);
		materiaEstudada.setConcursoMateria(concursoMateria);
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
				materiaEstudada.setConcursoMateria(concursoMateria.getConcursoMateria());
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

		materiaEstudada.setDataHoraEstudo(new Date());
		materiaEstudada
				.setTempoEstudadoInt((int) (horasEstudadaInMillis / 1000));

		try {
			userTransaction.begin();
			materiaEstudadaRepository.update(materiaEstudada);
			userTransaction.commit();
		} catch (SecurityException | IllegalStateException
				| NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
		materiasEstudadas = materiaEstudadaRepository.findAll();
		materiaEstudada = build();
	}

	public boolean getBtPausarDisabled() {
		return !btIniciarDisabled;
	}
}
