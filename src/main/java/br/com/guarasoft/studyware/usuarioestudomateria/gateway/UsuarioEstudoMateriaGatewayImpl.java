package br.com.guarasoft.studyware.usuarioestudomateria.gateway;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.converter.UsuarioEstudoEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter.UsuarioEstudoMateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateriaPK;

public class UsuarioEstudoMateriaGatewayImpl extends
		AbstractDao<UsuarioEstudoMateria, UsuarioEstudoMateriaPK> implements
		UsuarioEstudoMateriaGateway {

	public UsuarioEstudoMateriaGatewayImpl() {
		super(UsuarioEstudoMateria.class);
	}

	@Inject
	private UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter;
	@Inject
	private UsuarioEstudoEntidadeConverter usuarioEstudoEntidadeConverter;
	@Inject
	private MateriaEntidadeConverter materiaEntidadeConverter;

	@Override
	public UsuarioEstudoMateriaBean find(UsuarioEstudoBean usuarioEstudoBean,
			MateriaBean materiaBean) {
		UsuarioEstudoMateriaPK pk = new UsuarioEstudoMateriaPK();
		pk.setUsuarioEstudo(this.usuarioEstudoEntidadeConverter
				.convert(usuarioEstudoBean));
		pk.setMateria(this.materiaEntidadeConverter.convert(materiaBean));

		UsuarioEstudoMateria entidade = this.entityManager.find(
				UsuarioEstudoMateria.class, pk);

		UsuarioEstudoMateriaBean bean = this.usuarioEstudoMateriaEntidadeConverter
				.convert(entidade);

		return bean;
	}

	@Override
	public List<UsuarioEstudoMateriaBean> findAll(
			UsuarioEstudoBean usuarioEstudoBean) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from UsuarioEstudoMateria uem ");
		sql.append("where uem.usuarioEstudoMateriaPK.usuarioEstudo.id = :usuarioEstudo");

		Query query = this.entityManager.createQuery(sql.toString(),
				UsuarioEstudoMateria.class);
		query.setParameter("usuarioEstudo", usuarioEstudoBean.getId());
		List<UsuarioEstudoMateria> entidades = query.getResultList();

		List<UsuarioEstudoMateriaBean> beans = this.usuarioEstudoMateriaEntidadeConverter
				.convert(entidades);

		return beans;
	}
}