package br.com.guarasoft.studyware.estudodiario.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.guarasoft.studyware.estudodiario.bean.EstudoDiarioBean;
import br.com.guarasoft.studyware.estudodiario.gateway.converter.EstudoDiarioEntidadeConverter;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiario;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public class EstudoDiaGatewayImpl extends AbstractDao<EstudoDiario, Long>
		implements EstudoDiaGateway {

	@Inject
	private EstudoDiarioEntidadeConverter estudoDiarioEntidadeConverter;

	public EstudoDiaGatewayImpl() {
		super(EstudoDiario.class);
	}

	@Override
	public List<EstudoDiarioBean> findAll(UsuarioEstudoBean estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT date_trunc('day', THE.DT_HORA_ESTUDO) AS dataInicioSemana, ");
		sql.append("       extract( DOW FROM THE.DT_HORA_ESTUDO ) AS diaSemana, ");
		sql.append("       SUM( THE.TEMPO_ESTUDADO ) AS tempoEstudadoLong, ");
		sql.append("       TED.TEMPO_ALOCADO, ");
		sql.append("       new br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiario( dataInicioSemana, diaSemana, TED.TEMPO_ALOCADO, tempoEstudadoLong ) ");
		sql.append("  FROM UsuarioEstudoMateriaHistorico THE ");
		sql.append("  LEFT OUTER JOIN EstudoUsuarioMateria THE.usuarioEstudoMateria EUM ");
		// sql.append("    ON THE.ID_ESTUDO_MATERIA = EUM.ID ");
		sql.append("  LEFT OUTER JOIN UsuarioEstudo EUM.usuarioEstudoMateriaPK.usuarioEstudo TE ");
		// sql.append("    ON EUM.ID_ESTUDO = TE.ID ");
		sql.append("  LEFT OUTER JOIN TB_ESTUDO_DIA TED ");
		sql.append("    ON TED.ID_ESTUDO = TE.ID ");
		sql.append("   AND TED.ID_DIA = extract( DOW FROM THE.DT_HORA_ESTUDO ) ");
		sql.append(" WHERE TE.ID = ? ");
		sql.append(" GROUP BY 1, 2, 4 ");
		sql.append(" ORDER BY 1 ");

		// Query query = entityManager.createQuery(sql.toString(),
		// EstudoDiario.class);
		// List<EstudoDiario> entidades = query.setParameter(1, estudo.getId())
		// .getResultList();

		List<EstudoDiario> entidades = new ArrayList<>();
		List<EstudoDiarioBean> beans = this.estudoDiarioEntidadeConverter
				.convert(entidades);

		return beans;
	}
}
