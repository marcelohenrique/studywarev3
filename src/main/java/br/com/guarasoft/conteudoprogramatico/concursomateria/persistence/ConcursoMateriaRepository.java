/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concursomateria.persistence;

import java.util.List;

import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;
import br.com.guarasoft.conteudoprogramatico.concursomateria.entidade.ConcursoMateria;

/**
 * @author guara
 * 
 */
public interface ConcursoMateriaRepository {
	public ConcursoMateria findById(Long id);
	/**
	 * Método para recuperar as matérias de um concurso.
	 * @param concurso TODO
	 * 
	 * @return Lista de matérias do concurso.
	 */
	public List<ConcursoMateria> findAll(Concurso concurso);
}
