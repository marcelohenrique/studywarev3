package br.com.guarasoft.studyware.estudosemanal.gateway;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudosemanal.bean.EstudoSemanalBean;
import br.com.guarasoft.studyware.estudosemanal.gateway.converter.EstudoSemanalEntidadeConverter;
import br.com.guarasoft.studyware.estudosemanal.gateway.entidade.EstudoSemanal;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

public class EstudoSemanalGatewayImpl extends AbstractDao<EstudoSemanal, Long>
		implements EstudoSemanalGateway {

	private final EstudoSemanalEntidadeConverter estudoSemanalEntidadeConverter = new EstudoSemanalEntidadeConverter();

	public EstudoSemanalGatewayImpl() {
		super(EstudoSemanal.class);
	}

	@Override
	public List<EstudoSemanalBean> findAll(Estudo estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new br.com.guarasoft.studyware.estudosemanal.gateway.entidade.EstudoSemanal( date_trunc( 'week', emh.horaEstudo ), SUM( emh.tempoEstudado ) ) ");
		sql.append("  FROM EstudoMateriaHistorico emh ");
		sql.append(" WHERE emh.estudo.id = :idEstudo ");
		sql.append(" GROUP BY date_trunc( 'week', emh.horaEstudo ) ");
		sql.append(" ORDER BY date_trunc( 'week', emh.horaEstudo ) DESC");

		TypedQuery<EstudoSemanal> query = this.entityManager.createQuery(
				sql.toString(), EstudoSemanal.class);
		query.setParameter("idEstudo", estudo.getId());
		List<EstudoSemanal> estudosSemanais = query.getResultList();

		List<EstudoSemanalBean> beans = this.estudoSemanalEntidadeConverter
				.convert(estudosSemanais);

		return beans;
	}
}
