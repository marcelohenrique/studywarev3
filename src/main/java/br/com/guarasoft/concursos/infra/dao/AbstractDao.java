/**
 * 
 */
package br.com.guarasoft.concursos.infra.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author guara
 * 
 */
public abstract class AbstractDao<T extends Entidade, PK extends Serializable>
		implements Dao<T, PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5697060910262570538L;

	private Class<T> clazz;

	@PersistenceContext(unitName = "concurso")
	private EntityManager entityManager;

	/**
	 * 
	 */
	public AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void persist(T t) {
		entityManager.persist(t);
	}

	@Override
	public T find(PK pk) {
		return entityManager.find(clazz, pk);
	}

	/*
	 * @Override public List<T> findAll() { return null; }
	 */

	@Override
	public T update(T t) {
		return entityManager.merge(t);
	}

	@Override
	public void delete(T t) {
		entityManager.remove(t);
	}

}
