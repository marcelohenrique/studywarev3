/**
 * 
 */
package br.com.guarasoft.studyware.estudosemanal.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.estudosemanal.entidade.EstudoSemanal;

/**
 * @author guara
 * 
 */
public interface EstudoSemanalDao {
	public List<EstudoSemanal> findAll(Estudo estudo);
}
