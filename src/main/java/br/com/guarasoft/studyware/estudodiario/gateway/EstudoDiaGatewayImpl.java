package br.com.guarasoft.studyware.estudodiario.gateway;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudodiario.gateway.converter.EstudoDiarioEntidadeConverter;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiarioTransformer;
import br.com.guarasoft.studyware.estudodiario.modelo.EstudoSemanal;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

public class EstudoDiaGatewayImpl extends AbstractDao<EstudoDiarioTransformer, Long>
		implements EstudoDiaGateway {

	private final EstudoDiarioEntidadeConverter estudoDiarioEntidadeConverter = new EstudoDiarioEntidadeConverter();

	public EstudoDiaGatewayImpl() {
		super(EstudoDiarioTransformer.class);
	}

	@Override
	public List<EstudoSemanal> findAll(String nomeEstudo, String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiarioTransformer( date_trunc( 'day', emhe.horaEstudo ), extract( DOW FROM emhe.horaEstudo ), ed.tempoAlocado, SUM( emhe.tempoEstudado ) ) ");
		sql.append("  FROM EstudoMateriaHistoricoEntidade emhe ");
		sql.append("  JOIN emhe.estudoMateria.estudo.participantes p ");
		sql.append("  LEFT OUTER JOIN emhe.estudoMateria.estudo.estudoDiarios ed ");
		sql.append(" WHERE emhe.estudoMateria.estudo.nome = :nome ");
		sql.append("   AND p.usuario.email = :email ");
		sql.append("   AND ed.dia = extract( DOW FROM emhe.horaEstudo ) ");
		sql.append(" GROUP BY date_trunc( 'day', emhe.horaEstudo ), extract( DOW FROM emhe.horaEstudo ), ed.tempoAlocado ");
		sql.append(" ORDER BY date_trunc( 'day', emhe.horaEstudo ) ");

		TypedQuery<EstudoDiarioTransformer> query = this.entityManager.createQuery(
				sql.toString(), EstudoDiarioTransformer.class);
		query.setParameter("nome", nomeEstudo);
		query.setParameter("email", email);
		List<EstudoDiarioTransformer> entidades = query.getResultList();

		List<EstudoSemanal> beans = this.estudoDiarioEntidadeConverter
				.convert(entidades);

		return beans;
	}
}
