/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence;

import java.util.List;

import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;

/**
 * @author guara
 * 
 */
public interface EstudoSemanalRepository {
	public List<EstudoSemanal> findAll(Concurso concurso);
}
