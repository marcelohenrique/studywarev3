package br.com.guarasoft.studyware.estudosemanal.gateway;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudosemanal.bean.EstudoSemanalBean;
import br.com.guarasoft.studyware.estudosemanal.gateway.converter.EstudoSemanalEntidadeConverter;
import br.com.guarasoft.studyware.estudosemanal.gateway.entidade.EstudoSemanal;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

public class EstudoSemanalGatewayImpl extends AbstractDao<EstudoSemanal, Long> implements EstudoSemanalGateway {

	private final EstudoSemanalEntidadeConverter estudoSemanalEntidadeConverter = new EstudoSemanalEntidadeConverter();

	public EstudoSemanalGatewayImpl() {
		super(EstudoSemanal.class);
	}

	@Override
	public List<EstudoSemanalBean> findAll(Estudo estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new br.com.guarasoft.studyware.estudosemanal.gateway.entidade.EstudoSemanal( date_trunc( 'week', emhe.horaEstudo ), SUM( emhe.tempoEstudado ) ) ");
		sql.append("  FROM EstudoMateriaHistoricoEntidade emhe ");
		sql.append("  LEFT OUTER JOIN emhe.estudoMateria em ");
		sql.append(" WHERE em.estudo.nome = ? ");
		sql.append(" GROUP BY date_trunc( 'week', emhe.horaEstudo ) ");
		sql.append(" ORDER BY date_trunc( 'week', emhe.horaEstudo ) ");

		TypedQuery<EstudoSemanal> query = this.entityManager.createQuery(sql.toString(), EstudoSemanal.class);
		query.setParameter(1, estudo.getNome());
		List<EstudoSemanal> estudosSemanais = query.getResultList();

		List<EstudoSemanalBean> beans = this.estudoSemanalEntidadeConverter.convert(estudosSemanais);

		return beans;
	}
}
