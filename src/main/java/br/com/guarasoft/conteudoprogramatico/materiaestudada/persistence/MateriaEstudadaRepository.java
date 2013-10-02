/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence;

import java.util.List;

/**
 * @author guara
 * 
 */
public interface MateriaEstudadaRepository {
	public List<MateriaEstudada> findAll();
	public void persist(MateriaEstudada t);
	public MateriaEstudada update( MateriaEstudada t );
}
