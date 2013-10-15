/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concursomateriaestudada;

import java.util.List;

import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;

/**
 * @author guara
 * 
 */
public interface ConcursoMateriaEstudadaRepository {
	public List<ConcursoMateriaEstudada> findAll(Concurso concurso);
}
