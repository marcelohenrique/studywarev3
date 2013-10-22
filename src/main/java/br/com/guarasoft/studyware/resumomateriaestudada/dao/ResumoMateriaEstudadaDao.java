/**
 * 
 */
package br.com.guarasoft.studyware.resumomateriaestudada.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.resumomateriaestudada.entidade.ResumoMateriaEstudada;

/**
 * @author guara
 * 
 */
public interface ResumoMateriaEstudadaDao {
	public List<ResumoMateriaEstudada> findAll(Estudo estudo);
}
