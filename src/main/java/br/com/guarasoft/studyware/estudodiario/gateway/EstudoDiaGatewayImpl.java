package br.com.guarasoft.studyware.estudodiario.gateway;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudodiario.gateway.converter.EstudoDiarioEntidadeConverter;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiarioTransformer;
import br.com.guarasoft.studyware.estudodiario.modelo.EstudoSemanal;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

public class EstudoDiaGatewayImpl extends
		AbstractDao<EstudoDiarioTransformer, Long> implements EstudoDiaGateway {

	private final EstudoDiarioEntidadeConverter estudoDiarioEntidadeConverter = new EstudoDiarioEntidadeConverter();

	public EstudoDiaGatewayImpl() {
		super(EstudoDiarioTransformer.class);
	}

	@Override
	public List<EstudoSemanal> findAll(Long idEstudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiarioTransformer( date_trunc( 'day', emh.horaEstudo ), extract( DOW FROM emh.horaEstudo ), ed.tempoAlocado, SUM( emh.tempoEstudado ) ) ");
		sql.append("  FROM EstudoMateriaHistorico emh ");
		sql.append("  LEFT OUTER JOIN emh.estudo.estudoDiarios ed ");
		sql.append(" WHERE emh.estudo.id = :idEstudo ");
		sql.append("   AND ed.dia = extract( DOW FROM emh.horaEstudo ) ");
		sql.append(" GROUP BY date_trunc( 'day', emh.horaEstudo ), extract( DOW FROM emh.horaEstudo ), ed.tempoAlocado ");
		sql.append(" ORDER BY date_trunc( 'day', emh.horaEstudo ) ");

		TypedQuery<EstudoDiarioTransformer> query = this.entityManager
				.createQuery(sql.toString(), EstudoDiarioTransformer.class);
		query.setParameter("idEstudo", idEstudo);
		List<EstudoDiarioTransformer> entidades = query.getResultList();

		List<EstudoSemanal> beans = this.estudoDiarioEntidadeConverter
				.convert(entidades);

		return beans;
	}
}
