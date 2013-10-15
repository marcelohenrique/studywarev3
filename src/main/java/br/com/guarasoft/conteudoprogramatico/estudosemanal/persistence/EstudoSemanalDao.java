/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;
import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;

/**
 * @author guara
 * 
 */
public class EstudoSemanalDao extends AbstractDao<EstudoSemanal, Long>
		implements EstudoSemanalRepository {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	public EstudoSemanalDao() {
		super(EstudoSemanal.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence.
	 * EstudoSemanalRepository#findAll()
	 */
	@Override
	public List<EstudoSemanal> findAll(Concurso concurso) {
		Query query = entityManager
				.createNativeQuery(
						"SELECT date_trunc('week', THE.DATA_HORA_ESTUDO) AS \"dataInicioSemana\" , SUM( THE.TEMPO_ESTUDADO ) AS \"tempoEstudadoLong\" FROM TB_HISTORICO_ESTUDO THE RIGHT OUTER JOIN TB_CONCURSO_MATERIA TCM ON THE.ID_CONCURSO_MATERIA = TCM.ID WHERE TCM.ID_CONCURSO = ? GROUP BY 1 ORDER BY 1",
						EstudoSemanal.class);
		List<EstudoSemanal> estudosSemanais = query.setParameter(1,
				concurso.getId()).getResultList();
		Duration estudoSemanalAcumuladoParcial = new Duration(0);
		for (EstudoSemanal estudoSemanal : estudosSemanais) {
			if (estudoSemanal != null) {
				estudoSemanalAcumuladoParcial = estudoSemanalAcumuladoParcial
						.plus(estudoSemanal.getTempoEstudado().getMillis());
				estudoSemanal
						.setTempoEstudadoAcumulado(estudoSemanalAcumuladoParcial
								.toDuration());
			}
		}
		return estudosSemanais;
	}

}
