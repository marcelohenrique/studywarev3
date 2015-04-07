package br.com.guarasoft.studyware.estudomateria.gateway;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.estudomateria.gateway.converter.UsuarioEstudoMateriaBuilder;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.EstudoMateriaEntidade;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

public class UsuarioEstudoMateriaGatewayImpl extends
		AbstractDao<EstudoMateriaEntidade, Long> implements
		UsuarioEstudoMateriaGateway {

	private final UsuarioEstudoMateriaBuilder usuarioEstudoMateriaBuilder = new UsuarioEstudoMateriaBuilder();

	public UsuarioEstudoMateriaGatewayImpl() {
		super(EstudoMateriaEntidade.class);
	}

	@Override
	public UsuarioEstudoMateriaBean buscaPorId(Long id) {
		EstudoMateriaEntidade entidade = this.find(id);

		UsuarioEstudoMateriaBean bean = this.usuarioEstudoMateriaBuilder
				.convert(entidade);

		return bean;
	}

	@Override
	public List<UsuarioEstudoMateriaBean> buscaPorUsuarioEstudo(
			String nomeEstudo, String emailUsuario) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from EstudoMateriaEntidade uem ");
		sql.append("where uem.estudo.nome = :nomeEstudo ");
		sql.append("where uem.estudo.usuarios.email = :usuarioEmail ");
		sql.append("order by uem.materia.nome ");

		TypedQuery<EstudoMateriaEntidade> query = this.entityManager
				.createQuery(sql.toString(), EstudoMateriaEntidade.class);
		query.setParameter("nomeEstudo", nomeEstudo);
		query.setParameter("usuarioEmail", emailUsuario);
		List<EstudoMateriaEntidade> entidades = query.getResultList();

		List<UsuarioEstudoMateriaBean> beans = this.usuarioEstudoMateriaBuilder
				.convert(null, entidades);

		return beans;
	}

}