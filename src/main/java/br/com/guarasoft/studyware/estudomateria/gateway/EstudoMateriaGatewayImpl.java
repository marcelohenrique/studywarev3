package br.com.guarasoft.studyware.estudomateria.gateway;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateria.gateway.converter.UsuarioEstudoMateriaBuilder;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.EstudoMateriaEntidade;
import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

public class EstudoMateriaGatewayImpl extends
		AbstractDao<EstudoMateriaEntidade, Long> implements
		EstudoMateriaGateway {

	private final UsuarioEstudoMateriaBuilder usuarioEstudoMateriaBuilder = new UsuarioEstudoMateriaBuilder();

	public EstudoMateriaGatewayImpl() {
		super(EstudoMateriaEntidade.class);
	}

	@Override
	public EstudoMateria buscaPorId(Long id) {
		EstudoMateriaEntidade entidade = this.find(id);

		EstudoMateria bean = this.usuarioEstudoMateriaBuilder.convert(entidade);

		return bean;
	}

	@Override
	public List<EstudoMateria> buscaEstudoMateria(Estudo estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT em ");
		sql.append("  FROM EstudoMateria em ");
		// sql.append("  JOIN em.estudo.participantes p ");
		sql.append(" WHERE em.estudo.id = :idEstudo ");
		// sql.append("   AND p.usuario.email = :usuarioEmail ");
		sql.append(" ORDER BY em.ordem ");

		TypedQuery<EstudoMateriaEntidade> query = this.entityManager
				.createQuery(sql.toString(), EstudoMateriaEntidade.class);
		// query.setParameter("nomeEstudo", nomeEstudo);
		// query.setParameter("usuarioEmail", emailUsuario);
		query.setParameter("idEstudo", estudo.getId());
		List<EstudoMateriaEntidade> entidades = query.getResultList();

		List<EstudoMateria> beans = this.usuarioEstudoMateriaBuilder.convert(
				null, entidades);

		return beans;
	}

}