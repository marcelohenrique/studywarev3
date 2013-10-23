/**
 * 
 */
package br.com.guarasoft.studyware.materiaestudada.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.materiaestudada.entidade.MateriaEstudada;

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
	 * @see br.com.guarasoft.studyware.materiaestudada.dao.
	 * MateriaEstudadaDao#findAll()
	 */
	@Override
	public List<MateriaEstudada> findAll(Estudo estudo) {
		return entityManager
				.createQuery(
						"select me from MateriaEstudada me where me.estudoMateria.estudo.id = :estudo order by me.dataHoraEstudo desc",
						MateriaEstudada.class).setParameter("estudo", estudo.getId()).getResultList();
	}

}
