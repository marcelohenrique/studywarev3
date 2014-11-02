package br.com.guarasoft.studyware.estudodiario.gateway;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import br.com.guarasoft.studyware.estudodiario.bean.EstudoDiarioBean;
import br.com.guarasoft.studyware.estudodiario.gateway.converter.EstudoDiarioEntidadeConverter;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiario;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public class EstudoDiaGatewayImpl extends AbstractDao<EstudoDiario, Long> implements EstudoDiaGateway {

	@Inject
	private EstudoDiarioEntidadeConverter estudoDiarioEntidadeConverter;

	public EstudoDiaGatewayImpl() {
		super(EstudoDiario.class);
	}

	@Override
	public List<EstudoDiarioBean> findAll(UsuarioEstudoBean estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiario( date_trunc( 'day', uemh.horaEstudo ), extract( DOW FROM uemh.horaEstudo ), ued.tempoAlocado, SUM( uemh.tempoEstudado ) ) ");
		sql.append("  FROM UsuarioEstudoMateriaHistorico uemh ");
		sql.append("  LEFT OUTER JOIN uemh.usuarioEstudoMateria.pk.usuarioEstudo.usuarioEstudoDiarios ued ");
		sql.append(" WHERE uemh.usuarioEstudoMateria.pk.usuarioEstudo.id = ? ");
		sql.append("   AND ued.pk.dia = extract( DOW FROM uemh.horaEstudo ) ");
		sql.append(" GROUP BY date_trunc( 'day', uemh.horaEstudo ), extract( DOW FROM uemh.horaEstudo ), ued.tempoAlocado ");
		sql.append(" ORDER BY date_trunc( 'day', uemh.horaEstudo ) ");

		Query query = entityManager.createQuery(sql.toString(), EstudoDiario.class);
		List<EstudoDiario> entidades = query.setParameter(1, estudo.getId()).getResultList();

		List<EstudoDiarioBean> beans = this.estudoDiarioEntidadeConverter.convert(entidades);

		return beans;
	}
}
