package br.com.guarasoft.studyware.infra.dao;

import java.io.Serializable;
import java.util.List;

public interface Dao<T extends Entidade, PK extends Serializable> {
	public void persist(T t);

	public T find(PK pk);

	// public List<T> findAll();
	public List<T> findAll(String... orderByFields);

	public T merge(T t);

	public void remove(T t);

}
