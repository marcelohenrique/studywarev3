package br.com.guarasoft.studyware.estudosemanal.gateway;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import br.com.guarasoft.studyware.estudosemanal.bean.EstudoSemanalBean;
import br.com.guarasoft.studyware.estudosemanal.gateway.converter.EstudoSemanalEntidadeConverter;
import br.com.guarasoft.studyware.estudosemanal.gateway.entidade.EstudoSemanal;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public class EstudoSemanalGatewayImpl extends AbstractDao<EstudoSemanal, Long>
		implements EstudoSemanalGateway {

	@Inject
	private EstudoSemanalEntidadeConverter estudoSemanalEntidadeConverter;

	public EstudoSemanalGatewayImpl() {
		super(EstudoSemanal.class);
	}

	@Override
	public List<EstudoSemanalBean> findAll(UsuarioEstudoBean estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new br.com.guarasoft.studyware.estudosemanal.gateway.entidade.EstudoSemanal( date_trunc( 'week', uemh.horaEstudo ), SUM( uemh.tempoEstudado ) ) ");
		sql.append("  FROM UsuarioEstudoMateriaHistorico uemh ");
		sql.append("  LEFT OUTER JOIN uemh.usuarioEstudoMateria uem ");
		sql.append(" WHERE uem.usuarioEstudoMateriaPK.usuarioEstudo.id = ? ");
		sql.append(" GROUP BY date_trunc( 'week', uemh.horaEstudo ) ");
		sql.append(" ORDER BY date_trunc( 'week', uemh.horaEstudo ) ");

		Query query = entityManager.createQuery(sql.toString(),
				EstudoSemanal.class);
		query.setParameter(1, estudo.getId());
		List<EstudoSemanal> estudosSemanais = query.getResultList();

		// Duration estudoSemanalAcumuladoParcial = new Duration(0);
		// for (EstudoSemanal estudoSemanal : estudosSemanais) {
		// if (estudoSemanal != null) {
		// estudoSemanalAcumuladoParcial =
		// estudoSemanalAcumuladoParcial.plus(estudoSemanal.getTempoEstudado());
		// estudoSemanal.setTempoEstudadoAcumulado(estudoSemanalAcumuladoParcial.toDuration());
		// }
		// }

		List<EstudoSemanalBean> beans = this.estudoSemanalEntidadeConverter
				.convert(estudosSemanais);

		return beans;
	}
}
