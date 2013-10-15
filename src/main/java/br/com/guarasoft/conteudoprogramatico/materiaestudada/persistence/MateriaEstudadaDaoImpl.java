/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence;

import java.util.List;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;
import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;
import br.com.guarasoft.conteudoprogramatico.materiaestudada.entidade.MateriaEstudada;

/**
 * @author guara
 * 
 */
public class MateriaEstudadaDaoImpl extends AbstractDao<MateriaEstudada, Long>
		implements MateriaEstudadaDao {

	/**
	 */
	public MateriaEstudadaDaoImpl() {
		super(MateriaEstudada.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence.
	 * MateriaEstudadaDao#findAll()
	 */
	@Override
	public List<MateriaEstudada> findAll(Concurso concurso) {
		return entityManager
				.createQuery(
						"select me from MateriaEstudada me where me.concursoMateria.concurso.id = :concurso order by me.dataHoraEstudo desc",
						MateriaEstudada.class).setParameter("concurso", concurso.getId()).getResultList();
	}

}
