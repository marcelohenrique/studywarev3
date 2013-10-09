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

/**
 * @author guara
 * 
 */
public class EstudoSemanalDao extends AbstractDao<EstudoSemanal, Long>
		implements EstudoSemanalRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = -91192548671166121L;

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
	public List<EstudoSemanal> findAll() {
		Query query = entityManager
				.createNativeQuery(
						"SELECT date_trunc('week', DATA_HORA_ESTUDO) AS \"dataInicioSemana\" , SUM( TEMPO_ESTUDADO ) AS \"tempoEstudadoInt\" "
								+ "FROM tb_historico_estudo "
								+ "GROUP BY 1 "
								+ "ORDER BY 1", EstudoSemanal.class);
		List<EstudoSemanal> estudosSemanais = query.getResultList();
		Duration estudoSemanalAcumuladoParcial = new Duration(0);
		for (EstudoSemanal estudoSemanal : estudosSemanais) {
			estudoSemanalAcumuladoParcial = estudoSemanalAcumuladoParcial.plus(estudoSemanal.getTempoEstudado().getMillis());
			estudoSemanal.setTempoEstudadoAcumulado(estudoSemanalAcumuladoParcial.toDuration());
		}
		return estudosSemanais;
	}

}
