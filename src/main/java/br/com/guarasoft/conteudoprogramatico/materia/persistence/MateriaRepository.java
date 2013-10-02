/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materia.persistence;

import java.util.List;

/**
 * @author guara
 *
 */
public interface MateriaRepository {
	public List<Materia> findAll();
}
