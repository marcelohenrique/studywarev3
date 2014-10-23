package br.com.guarasoft.studyware.estudodiario.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.guarasoft.studyware.estudodiario.entidade.EstudoDiario;
import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

public class EstudoDiaDaoImpl extends AbstractDao<EstudoDiario, Long> implements
		EstudoDiaDao {

	public EstudoDiaDaoImpl() {
		super(EstudoDiario.class);
	}

	@Override
	public List<EstudoDiario> findAll(EstudoUsuarioBean estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT date_trunc('day', THE.DT_HORA_ESTUDO) AS dataInicioSemana, ");
		sql.append("       extract( DOW FROM THE.DT_HORA_ESTUDO ) AS diaSemana, ");
		sql.append("       SUM( THE.TEMPO_ESTUDADO ) AS tempoEstudadoLong, ");
		sql.append("       TED.TEMPO_ALOCADO ");
		sql.append("  FROM TB_HISTORICO_ESTUDO THE ");
		sql.append(" RIGHT OUTER JOIN TB_ESTUDO_MATERIA TEM ");
		sql.append("    ON THE.ID_ESTUDO_MATERIA = TEM.ID ");
		sql.append(" RIGHT OUTER JOIN EstudoUsuario TE ");
		sql.append("    ON TEM.ID_ESTUDO = TE.ID ");
		sql.append("  LEFT OUTER JOIN TB_ESTUDO_DIA TED ");
		sql.append("    ON TED.ID_ESTUDO = TE.ID ");
		sql.append("   AND TED.ID_DIA = extract( DOW FROM THE.DT_HORA_ESTUDO ) ");
		sql.append(" WHERE TE.ID = ? ");
		sql.append(" GROUP BY 1, 2, 4 ");
		sql.append(" ORDER BY 1 ");

		Query query = entityManager.createNativeQuery(sql.toString(),
				EstudoDiario.class);
		List<EstudoDiario> estudosDiarios = query.setParameter(1,
				estudo.getId()).getResultList();
		return estudosDiarios;
	}

}
