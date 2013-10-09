/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materia.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;

/**
 * @author guara
 * 
 */
public class MateriaDao extends AbstractDao<Materia, Long> implements
		MateriaRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2535750489065624202L;
	
	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	public MateriaDao() {
		super(Materia.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.guarasoft.conteudoprogramatico.materia.persistence.repository.
	 * MateriaRepository#findAll()
	 */
	@Override
	public List<Materia> findAll() {
		TypedQuery<Materia> typedQuery = entityManager.createQuery("select m from Materia m", Materia.class);
		return typedQuery.getResultList();
	}

}
