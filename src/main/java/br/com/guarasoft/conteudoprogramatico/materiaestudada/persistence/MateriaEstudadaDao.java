/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence;

import java.util.List;

import br.com.guarasoft.concursos.infra.dao.Dao;
import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.entidade.MateriaEstudada;

/**
 * @author guara
 *
 */
public interface MateriaEstudadaDao extends Dao<MateriaEstudada, Long> {
	public List<MateriaEstudada> findAll(Concurso concurso);
}
