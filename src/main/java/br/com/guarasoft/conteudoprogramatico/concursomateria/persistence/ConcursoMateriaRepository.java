/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concursomateria.persistence;

import java.util.List;

/**
 * @author guara
 * 
 */
public interface ConcursoMateriaRepository {
	public List<ConcursoMateriaEstudada> findAll();
}
