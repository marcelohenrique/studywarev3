/**
 * 
 */
package br.com.guarasoft.studyware.materiaestudada.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.materiaestudada.entidade.MateriaEstudada;

/**
 * @author guara
 *
 */
public interface MateriaEstudadaDao {
	public void persist( MateriaEstudada materiaEstudada );
	public List<MateriaEstudada> findAll(Estudo estudo);
}
