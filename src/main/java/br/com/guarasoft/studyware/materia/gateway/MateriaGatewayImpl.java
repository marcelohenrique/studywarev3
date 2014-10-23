package br.com.guarasoft.studyware.materia.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;

@Stateless
public class MateriaGatewayImpl implements MateriaGateway {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Override
	public List<MateriaBean> buscaMaterias() {
		TypedQuery<Materia> typedQuery = entityManager.createQuery(
				"from Materia m order by m.sigla", Materia.class);

		List<Materia> materias = typedQuery.getResultList();

		List<MateriaBean> materiasBean = converteMateriaParaMateriaBean(materias);

		return materiasBean;
	}

	private List<MateriaBean> converteMateriaParaMateriaBean(
			List<Materia> materias) {
		List<MateriaBean> materiasBean = new ArrayList<>();
		for (Materia materia : materias) {
			materiasBean.add(converteMateriaParaMateriaBean(materia));
		}
		return materiasBean;
	}

	private MateriaBean converteMateriaParaMateriaBean(Materia materia) {
		MateriaBean materiaBean = new MateriaBean();

		materiaBean.setId(materia.getId());
		materiaBean.setSigla(materia.getSigla());
		materiaBean.setNome(materia.getNome());

		return materiaBean;
	}

	@Override
	public void cadastrar(MateriaBean materiaBean) {
		Materia materia = converteMateriaBeanParaMateria(materiaBean);

		this.entityManager.persist(materia);
	}

	private Materia converteMateriaBeanParaMateria(MateriaBean materiaBean) {
		Materia materia = new Materia();

		materia.setId(materiaBean.getId());
		materia.setSigla(materiaBean.getSigla());
		materia.setNome(materiaBean.getNome());

		return materia;
	}

	@Override
	public void alterar(MateriaBean materiaAlterada) {
		Materia materia = converteMateriaBeanParaMateria(materiaAlterada);

		this.entityManager.merge(materia);
	}

}
