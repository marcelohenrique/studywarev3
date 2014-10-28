package br.com.guarasoft.studyware.materia.gateway;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;

@Stateless
public class MateriaGatewayImpl extends AbstractDao<Materia, Long> implements
		MateriaGateway {

	@Inject
	private MateriaEntidadeConverter converter;

	public MateriaGatewayImpl() {
		super(Materia.class);
	}

	@Override
	public List<MateriaBean> buscaMaterias() {
		TypedQuery<Materia> typedQuery = entityManager.createQuery(
				"from Materia m order by m.sigla", Materia.class);

		List<Materia> materias = typedQuery.getResultList();

		List<MateriaBean> materiasBean = this.converter.convert(materias);

		return materiasBean;
	}

	@Override
	public void cadastrar(MateriaBean materiaBean) {
		Materia materia = this.converter.convert(materiaBean);

		// this.entityManager.persist(materia);
		this.persist(materia);
	}

	@Override
	public void alterar(MateriaBean materiaBean) {
		Materia materia = this.converter.convert(materiaBean);

		// this.entityManager.merge(materia);
		this.merge(materia);
	}

	@Override
	public void remover(MateriaBean materiaBean) {
		Materia materia = this.converter.convert(materiaBean);

		this.remove(this.merge(materia));
	}

}
