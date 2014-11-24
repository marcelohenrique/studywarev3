package br.com.guarasoft.studyware.usuarioestudomateria.gateway;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter.UsuarioEstudoMateriaBuilder;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

public class UsuarioEstudoMateriaGatewayImpl extends AbstractDao<UsuarioEstudoMateria, Long> implements UsuarioEstudoMateriaGateway {

	private final UsuarioEstudoMateriaBuilder usuarioEstudoMateriaBuilder = new UsuarioEstudoMateriaBuilder();

	public UsuarioEstudoMateriaGatewayImpl() {
		super(UsuarioEstudoMateria.class);
	}

	@Override
	public UsuarioEstudoMateriaBean buscaPorId(Long id) {
		UsuarioEstudoMateria entidade = this.find(id);

		UsuarioEstudoMateriaBean bean = this.usuarioEstudoMateriaBuilder.convert(entidade);

		return bean;
	}

	@Override
	public List<UsuarioEstudoMateriaBean> buscaPorUsuarioEstudo(UsuarioEstudoBean usuarioEstudoBean) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from UsuarioEstudoMateria uem ");
		sql.append("where uem.usuarioEstudo.id = :usuarioEstudo ");
		sql.append("order by uem.materia.nome ");

		TypedQuery<UsuarioEstudoMateria> query = this.entityManager.createQuery(sql.toString(), UsuarioEstudoMateria.class);
		query.setParameter("usuarioEstudo", usuarioEstudoBean.getId());
		List<UsuarioEstudoMateria> entidades = query.getResultList();

		List<UsuarioEstudoMateriaBean> beans = this.usuarioEstudoMateriaBuilder.convert(usuarioEstudoBean, entidades);

		return beans;
	}

}