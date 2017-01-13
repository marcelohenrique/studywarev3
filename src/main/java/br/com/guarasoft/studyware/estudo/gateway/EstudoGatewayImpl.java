package br.com.guarasoft.studyware.estudo.gateway;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudo.gateway.converter.EstudoBuilder;
import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

@Stateless
public class EstudoGatewayImpl extends AbstractDao<EstudoEntidade, Long> implements EstudoGateway {

	private final EstudoBuilder builder = new EstudoBuilder();

	public EstudoGatewayImpl() {
		super(EstudoEntidade.class);
	}

	@Override
	public void cadastrar(Estudo estudo) {
		EstudoEntidade estudoEntidade = this.builder.converteMaterias().converteDias().convert(estudo);

		this.merge(estudoEntidade);
	}

	@Override
	public List<Estudo> recuperaTodosEstudos(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e ");
		sql.append("  FROM Estudo e ");
		sql.append("  JOIN e.participantes p ");
		sql.append(" WHERE p.usuario.email = :email ");
		sql.append(" ORDER BY e.fim, e.nome ");

		TypedQuery<EstudoEntidade> typedQuery = this.entityManager.createQuery(sql.toString(), EstudoEntidade.class);

		typedQuery.setParameter("email", email);

		List<EstudoEntidade> estudosEntidade = typedQuery.getResultList();

		List<Estudo> estudos = this.builder.converteMaterias().converteDias().convert(estudosEntidade);

		return estudos;
	}

	@Override
	public List<Estudo> recuperaEstudosValidos(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e ");
		sql.append("  FROM Estudo e ");
		sql.append("  JOIN e.participantes p ");
		sql.append(" WHERE p.usuario.email = :email ");
		// sql.append(" AND ( e.fim >= current_date ");
		// sql.append(" OR e.fim IS NULL ) ");

		TypedQuery<EstudoEntidade> typedQuery = this.entityManager.createQuery(sql.toString(), EstudoEntidade.class);

		typedQuery.setParameter("email", email);

		List<EstudoEntidade> estudosEntidade = typedQuery.getResultList();

		List<Estudo> estudos = this.builder.converteMaterias().converteDias().convert(estudosEntidade);

		return estudos;
	}

	@Override
	public Estudo buscaPorId(Long id) {
		EstudoEntidade entidade = this.find(id);

		Estudo bean = this.builder.convert(entidade);

		return bean;
	}

	@Override
	public void remover(Estudo estudo) {
		EstudoEntidade estudoEntidade = this.buscaPorNome(estudo.getNome());

		// EstudoEntidade estudoEntidade = this.builder.convert(estudo);

		this.remove(estudoEntidade);
	}

	private EstudoEntidade buscaPorNome(String nome) {
		TypedQuery<EstudoEntidade> typedQuery = this.entityManager
				.createQuery("SELECT e FROM Estudo e WHERE e.nome = :nome", EstudoEntidade.class);
		typedQuery.setParameter("nome", nome);
		return typedQuery.getSingleResult();
	}

}
