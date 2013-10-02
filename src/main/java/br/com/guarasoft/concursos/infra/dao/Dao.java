/**
 * 
 */
package br.com.guarasoft.concursos.infra.dao;

import java.io.Serializable;

/**
 * @author guara
 *
 */
public interface Dao<T extends Entidade, PK extends Serializable> extends Serializable {
	public void persist(T t);
	public T find(PK pk);
	// public List<T> findAll();
	public T update( T t );
	public void delete( T t );
}
