/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.controleestudo.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import lombok.Getter;

import org.joda.time.Duration;

import br.com.guarasoft.conteudoprogramatico.concursomateriaestudada.ConcursoMateriaEstudada;
import br.com.guarasoft.conteudoprogramatico.concursomateriaestudada.ConcursoMateriaEstudadaRepository;

/**
 * @author guara
 * 
 */
@ManagedBean(name = "materiastable")
@ViewScoped
public class MateriasTableMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8416257459782698951L;

	@Getter
	private List<ConcursoMateriaEstudada> concursoMaterias = new ArrayList<>();
	@Getter
	private Duration tempoTotalAlocado = new Duration(0);
	@Getter
	private Duration tempoEstudadoTotal = new Duration(0);

	@Inject
	private ConcursoMateriaEstudadaRepository ConcursoMateriaEstudadaRepository;

	@PostConstruct
	public void init() {
		concursoMaterias = ConcursoMateriaEstudadaRepository.findAll();

		for (ConcursoMateriaEstudada concursoMateria : concursoMaterias) {
			tempoTotalAlocado = tempoTotalAlocado.plus(concursoMateria.getConcursoMateria()
					.getTempoAlocado());
			tempoEstudadoTotal = tempoEstudadoTotal.plus(concursoMateria
					.getSomaTempo());
		}
	}

}
