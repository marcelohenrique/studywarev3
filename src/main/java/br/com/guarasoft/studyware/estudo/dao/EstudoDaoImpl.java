/**
 * 
 */
package br.com.guarasoft.studyware.estudo.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

/**
 * @author guara
 *
 */
public class EstudoDaoImpl extends AbstractDao<Estudo, Long> implements EstudoDao {

	/**
	 * 
	 */
	public EstudoDaoImpl() {
		super(Estudo.class);
	}

	@Override
	public List<Estudo> findAll() {
		TypedQuery<Estudo> typedQuery = entityManager.createQuery("select e from Estudo e", Estudo.class);
		return typedQuery.getResultList();
	}

}
