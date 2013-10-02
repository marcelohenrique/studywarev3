/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence;

import java.util.List;

/**
 * @author guara
 * 
 */
public interface EstudoSemanalRepository {
	public List<EstudoSemanal> findAll();
}
