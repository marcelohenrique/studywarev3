package br.com.guarasoft.studyware.estudomateria.gateway;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudomateria.gateway.converter.UsuarioEstudoMateriaBuilder;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.EstudoMateriaEntidade;
import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

public class UsuarioEstudoMateriaGatewayImpl extends
		AbstractDao<EstudoMateriaEntidade, Long> implements
		UsuarioEstudoMateriaGateway {

	private final UsuarioEstudoMateriaBuilder usuarioEstudoMateriaBuilder = new UsuarioEstudoMateriaBuilder();

	public UsuarioEstudoMateriaGatewayImpl() {
		super(EstudoMateriaEntidade.class);
	}

	@Override
	public EstudoMateria buscaPorId(Long id) {
		EstudoMateriaEntidade entidade = this.find(id);

		EstudoMateria bean = this.usuarioEstudoMateriaBuilder
				.convert(entidade);

		return bean;
	}

	@Override
	public List<EstudoMateria> buscaPorUsuarioEstudo(
			String nomeEstudo, String emailUsuario) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT uem ");
		sql.append("  FROM EstudoMateriaEntidade uem ");
		sql.append("  JOIN uem.estudo.participantes p ");
		sql.append(" WHERE uem.estudo.nome = :nomeEstudo ");
		sql.append("   AND p.usuario.email = :usuarioEmail ");
		sql.append(" ORDER BY uem.materia.nome ");

		TypedQuery<EstudoMateriaEntidade> query = this.entityManager
				.createQuery(sql.toString(), EstudoMateriaEntidade.class);
		query.setParameter("nomeEstudo", nomeEstudo);
		query.setParameter("usuarioEmail", emailUsuario);
		List<EstudoMateriaEntidade> entidades = query.getResultList();

		List<EstudoMateria> beans = this.usuarioEstudoMateriaBuilder
				.convert(null, entidades);

		return beans;
	}

}