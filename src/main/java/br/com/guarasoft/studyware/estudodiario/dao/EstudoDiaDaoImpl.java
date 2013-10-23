/**
 * 
 */
package br.com.guarasoft.studyware.estudodiario.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.estudodiario.entidade.EstudoDiario;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

/**
 * @author guara
 * 
 */
public class EstudoDiaDaoImpl extends AbstractDao<EstudoDiario, Long> implements
		EstudoDiaDao {

	public EstudoDiaDaoImpl() {
		super(EstudoDiario.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.guarasoft.studyware.estudodiario.dao.EstudoDiaDao#findAll(br.com.
	 * guarasoft.studyware.estudo.entidade.Estudo)
	 */
	@Override
	public List<EstudoDiario> findAll(Estudo estudo) {
		Query query = entityManager
				.createNativeQuery(
						"SELECT date_trunc('day', THE.DT_HORA_ESTUDO) AS dataInicioSemana,  extract( DOW FROM THE.DT_HORA_ESTUDO ) AS diaSemana,  SUM( THE.TEMPO_ESTUDADO ) AS tempoEstudadoLong,  TED.TEMPO_ALOCADO FROM TB_HISTORICO_ESTUDO THE RIGHT OUTER JOIN TB_ESTUDO_MATERIA TEM ON THE.ID_ESTUDO_MATERIA = TEM.ID RIGHT OUTER JOIN TB_ESTUDO TE ON TEM.ID_ESTUDO = TE.ID LEFT OUTER JOIN TB_ESTUDO_DIA TED ON TED.ID_ESTUDO = TE.ID AND TED.ID_DIA = extract( DOW FROM THE.DT_HORA_ESTUDO ) WHERE TE.ID = ? GROUP BY 1, 2, 4 ORDER BY 1",
						EstudoDiario.class);
		List<EstudoDiario> estudosDiarios = query.setParameter(1,
				estudo.getId()).getResultList();
		return estudosDiarios;
	}

}
