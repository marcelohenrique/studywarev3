/**
 * 
 */
package br.com.guarasoft.studyware.estudo.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;

/**
 * @author guara
 * 
 */
public interface EstudoDao {

	public List<Estudo> findAll();

}
