/**
 * 
 */
package br.com.guarasoft.studyware.materia.persistence;

import java.util.List;

import br.com.guarasoft.studyware.materia.entidade.Materia;

/**
 * @author guara
 *
 */
public interface MateriaDao {
	public List<Materia> findAll();
}
