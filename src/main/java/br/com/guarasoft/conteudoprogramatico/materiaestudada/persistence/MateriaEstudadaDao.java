/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;

/**
 * @author guara
 * 
 */
public class MateriaEstudadaDao extends
		AbstractDao<MateriaEstudada, MateriaEstudadaPK> implements
		MateriaEstudadaRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5340200243706726827L;

	@PersistenceContext( unitName = "concurso" )
	private EntityManager entityManager;

	/**
	 * @param clazz
	 */
	public MateriaEstudadaDao() {
		super(MateriaEstudada.class);
	}

	@Override
	public List<MateriaEstudada> findAll() {
		TypedQuery<MateriaEstudada> typedQuery = entityManager
				.createQuery(
						"select me from MateriaEstudada me order by me.dataHoraEstudo desc",
						MateriaEstudada.class);
		return typedQuery.getResultList();
	}

}
