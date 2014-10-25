package br.com.guarasoft.studyware.usuarioestudomateria.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.converter.UsuarioEstudoEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

public class UsuarioEstudoMateriaGatewayImpl implements UsuarioEstudoMateriaGateway {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Inject
	private UsuarioEstudoEntidadeConverter usuarioEstudoConverter;
	@Inject
	private MateriaEntidadeConverter materiaConverter;

	@Override
	public UsuarioEstudoMateriaBean findById(Long id) {

		UsuarioEstudoMateria entidade = this.entityManager.find(
				UsuarioEstudoMateria.class, id);

		UsuarioEstudoMateriaBean bean = new UsuarioEstudoMateriaBean();

		bean.setUsuarioEstudoBean(this.usuarioEstudoConverter.convert(entidade
				.getUsuarioEstudoMateriaPK().getUsuarioEstudo()));
		bean.setMateriaBean(this.materiaConverter.convert(entidade
				.getUsuarioEstudoMateriaPK().getMateria()));

		return bean;
	}

	@Override
	public List<UsuarioEstudoMateriaBean> findAll(
			UsuarioEstudoBean usuarioEstudoBean) {
		Query query = this.entityManager
				.createQuery(
						"from UsuarioEstudoMateria cm where cm.usuarioEstudo.id = :usuarioEstudo",
						UsuarioEstudoMateria.class);
		query.setParameter("usuarioEstudo", usuarioEstudoBean.getId());
		List<UsuarioEstudoMateria> materias = query.getResultList();

		List<UsuarioEstudoMateriaBean> materiasBean = new ArrayList<>();

		// TODO

		return materiasBean;
	}
}