/**
 * 
 */
package br.com.guarasoft.studyware.estudodiario.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.estudodiario.entidade.EstudoDiario;

/**
 * @author guara
 *
 */
public interface EstudoDiaDao {
	public List<EstudoDiario> findAll(Estudo estudo);
}
