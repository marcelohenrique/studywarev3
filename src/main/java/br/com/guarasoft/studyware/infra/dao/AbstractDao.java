package br.com.guarasoft.studyware.infra.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDao<T extends Entidade, PK extends Serializable> implements Dao<T, PK> {

	private final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

	private final Class<T> clazz;

	@PersistenceContext(unitName = "studyware")
	protected EntityManager entityManager;

	public AbstractDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void persist(T t) {
		this.entityManager.persist(t);
	}

	@Override
	public T find(PK pk) {
		return this.entityManager.find(this.clazz, pk);
	}

	/*
	 * @Override public List<T> findAll() { return null; }
	 */

	// TODO: Avaliar renomear para saveOrUpdate e eliminar o persist.
	@Override
	public T merge(T t) {
		this.logger.debug(this.entityManager.toString());
		return this.entityManager.merge(t);
	}

	@Override
	public void remove(T t) {
		this.entityManager.remove(this.merge(t));
	}

}
