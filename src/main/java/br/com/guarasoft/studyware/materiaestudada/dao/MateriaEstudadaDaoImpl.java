package br.com.guarasoft.studyware.materiaestudada.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.materiaestudada.entidade.MateriaEstudada;

public class MateriaEstudadaDaoImpl extends AbstractDao<MateriaEstudada, Long>
		implements MateriaEstudadaDao {

	public MateriaEstudadaDaoImpl() {
		super(MateriaEstudada.class);
	}

	@Override
	public List<MateriaEstudada> findAll(EstudoUsuarioBean estudo) {
		return entityManager
				.createQuery(
						"select me from MateriaEstudada me where me.estudoMateria.estudo.id = :estudo order by me.dataHoraEstudo desc",
						MateriaEstudada.class)
				.setParameter("estudo", estudo.getId()).getResultList();
	}

}
