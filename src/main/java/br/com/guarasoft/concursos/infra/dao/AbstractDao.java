/**
 * 
 */
package br.com.guarasoft.concursos.infra.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guara
 * 
 */
public abstract class AbstractDao<T extends Entidade, PK extends Serializable> {
	
	private final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

	private Class<T> clazz;

	@PersistenceContext(unitName = "studyware")
	protected EntityManager entityManager;

	/**
	 * 
	 */
	public AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	/* @Override */
	protected void persist(T t) {
		entityManager.persist(t);
	}

	/* @Override */
	protected T find(PK pk) {
		return entityManager.find(clazz, pk);
	}

	/*
	 * @Override public List<T> findAll() { return null; }
	 */

	// TODO: Avaliar renomear para saveOrUpdate e eliminar o persist.
	/* @Override */
	public T update(T t) {
		logger.debug(entityManager.toString());
		return entityManager.merge(t);
	}

	/* @Override */
	public void delete(T t) {
		entityManager.remove(t);
	}

}
