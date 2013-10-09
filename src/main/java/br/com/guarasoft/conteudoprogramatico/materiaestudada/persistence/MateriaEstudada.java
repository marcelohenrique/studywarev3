/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence;

import java.util.List;

import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.Entidade;

/**
 * @author guara
 * 
 */
public interface MateriaEstudada extends Entidade {

	public void setMateriaEstudadaPK(MateriaEstudadaPK materiaEstudadaPK);

	public MateriaEstudadaPK getMateriaEstudadaPK();

	public void setObservacao(String observacao);

	public String getObservacao();

	public void setTempoEstudado(Duration tempoEstudado);

	public Duration getTempoEstudado();

	public List<MateriaEstudada> findAll();

	public void saveOrUpdate();

}
