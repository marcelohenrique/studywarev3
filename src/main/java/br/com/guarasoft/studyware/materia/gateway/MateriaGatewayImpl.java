package br.com.guarasoft.studyware.materia.gateway;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;

@Stateless
public class MateriaGatewayImpl implements MateriaGateway {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Inject
	private MateriaEntidadeConverter converter;

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

		this.entityManager.persist(materia);
	}

	@Override
	public void alterar(MateriaBean materiaAlterada) {
		Materia materia = this.converter.convert(materiaAlterada);

		this.entityManager.merge(materia);
	}

}
