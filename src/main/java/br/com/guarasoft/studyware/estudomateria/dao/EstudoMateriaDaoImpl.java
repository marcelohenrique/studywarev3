package br.com.guarasoft.studyware.estudomateria.dao;

import java.util.List;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.estudomateria.entidade.EstudoMateria;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

public class EstudoMateriaDaoImpl extends AbstractDao<EstudoMateria, Long> implements EstudoMateriaDao {

	public EstudoMateriaDaoImpl() {
		super(EstudoMateria.class);
	}

	@Override
	public EstudoMateria findById(Long id) {
		return find(id);
	}

	@Override
	public List<EstudoMateria> findAll(Estudo estudo) {
		return entityManager
				.createQuery(
						"select cm from EstudoMateria cm where cm.estudo.id = :idEstudo",
						EstudoMateria.class)
				.setParameter("idEstudo", estudo.getId()).getResultList();
	}

}