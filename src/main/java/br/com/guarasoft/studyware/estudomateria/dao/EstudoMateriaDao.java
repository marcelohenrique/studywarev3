/**
 * 
 */
package br.com.guarasoft.studyware.estudomateria.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.estudomateria.entidade.EstudoMateria;

/**
 * @author guara
 * 
 */
public interface EstudoMateriaDao {
	public EstudoMateria findById(Long id);
	/**
	 * Método para recuperar as matérias de um estudo.
	 * @param estudo TODO
	 * 
	 * @return Lista de matérias do estudo.
	 */
	public List<EstudoMateria> findAll(Estudo estudo);
}
