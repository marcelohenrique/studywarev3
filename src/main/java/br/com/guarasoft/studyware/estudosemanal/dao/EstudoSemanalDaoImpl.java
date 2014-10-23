package br.com.guarasoft.studyware.estudosemanal.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudosemanal.entidade.EstudoSemanal;
import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

public class EstudoSemanalDaoImpl extends AbstractDao<EstudoSemanal, Long>
		implements EstudoSemanalDao {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	public EstudoSemanalDaoImpl() {
		super(EstudoSemanal.class);
	}

	@Override
	public List<EstudoSemanal> findAll(EstudoUsuarioBean estudo) {
		Query query = entityManager
				.createNativeQuery(
						"SELECT date_trunc('week', THE.DT_HORA_ESTUDO) AS \"dataInicioSemana\", SUM( THE.TEMPO_ESTUDADO ) AS \"tempoEstudadoLong\" FROM TB_HISTORICO_ESTUDO THE RIGHT OUTER JOIN TB_ESTUDO_MATERIA TEM ON THE.ID_ESTUDO_MATERIA = TEM.ID WHERE TEM.ID_ESTUDO = ? GROUP BY 1 ORDER BY 1",
						EstudoSemanal.class);
		query.setParameter(1, estudo.getId());
		List<EstudoSemanal> estudosSemanais = query.getResultList();
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
