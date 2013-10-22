/**
 * 
 */
package br.com.guarasoft.studyware.materia.persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.materia.entidade.Materia;

/**
 * @author guara
 * 
 */
public class MateriaDaoImpl extends AbstractDao<Materia, Long> implements
		MateriaDao {

	public MateriaDaoImpl() {
		super(Materia.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.guarasoft.studyware.materia.persistence.repository.
	 * MateriaDao#findAll()
	 */
	@Override
	public List<Materia> findAll() {
		TypedQuery<Materia> typedQuery = entityManager.createQuery("select m from Materia m", Materia.class);
		return typedQuery.getResultList();
	}

}
