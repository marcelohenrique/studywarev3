package br.com.guarasoft.studyware.estudomateriahistorico.gateway;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.converter.UsuarioEstudoMateriaHistoricoEntidadeConverter;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.EstudoMateriaHistoricoEntidade;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.EstudoMateriaHistorico;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.ResumoMateria;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.materia.modelo.Materia;

@Stateless
public class EstudoMateriaHistoricoGatewayImpl extends AbstractDao<EstudoMateriaHistoricoEntidade, Long>
		implements EstudoMateriaHistoricoGateway {

	private final UsuarioEstudoMateriaHistoricoEntidadeConverter usuarioEstudoMateriaHistoricoEntidadeConverter = new UsuarioEstudoMateriaHistoricoEntidadeConverter();

	public EstudoMateriaHistoricoGatewayImpl() {
		super(EstudoMateriaHistoricoEntidade.class);
	}

	@Override
	public void persist(EstudoMateriaHistorico materiaEstudada) {
		EstudoMateriaHistoricoEntidade entidade = this.usuarioEstudoMateriaHistoricoEntidadeConverter
				.convert(materiaEstudada);

		this.persist(entidade);
	}

	@Override
	public void merge(EstudoMateriaHistorico bean) {
		EstudoMateriaHistoricoEntidade entidade = this.usuarioEstudoMateriaHistoricoEntidadeConverter.convert(bean);

		this.merge(entidade);
	}

	@Override
	public List<EstudoMateriaHistorico> findAll(Estudo estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT emh ");
		sql.append(" FROM EstudoMateriaHistorico emh ");
		sql.append("WHERE emh.estudo.id = :idEstudo ");
		sql.append("ORDER BY emh.horaEstudo DESC");

		TypedQuery<EstudoMateriaHistoricoEntidade> query = this.entityManager.createQuery(sql.toString(),
				EstudoMateriaHistoricoEntidade.class);
		query.setParameter("idEstudo", estudo.getId());
		List<EstudoMateriaHistoricoEntidade> entidades = query.getResultList();

		List<EstudoMateriaHistorico> beans = this.usuarioEstudoMateriaHistoricoEntidadeConverter.convert(estudo,
				entidades);

		return beans;
	}

	@Override
	public List<EstudoMateriaHistorico> findAll(Estudo estudo, Materia materia) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT emh ");
		sql.append(" FROM EstudoMateriaHistorico emh ");
		sql.append("WHERE emh.estudo.id = :idEstudo ");
		sql.append("  AND emh.materia.id = :idMateria ");
		sql.append("ORDER BY emh.horaEstudo DESC");

		TypedQuery<EstudoMateriaHistoricoEntidade> query = this.entityManager.createQuery(sql.toString(),
				EstudoMateriaHistoricoEntidade.class);
		query.setParameter("idEstudo", estudo.getId());
		query.setParameter("idMateria", materia.getId());
		List<EstudoMateriaHistoricoEntidade> entidades = query.getResultList();

		List<EstudoMateriaHistorico> beans = this.usuarioEstudoMateriaHistoricoEntidadeConverter.convert(estudo,
				entidades);

		return beans;
	}

	@Override
	public List<EstudoMateriaHistorico> findAll(Materia materia) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT emh ");
		sql.append(" FROM EstudoMateriaHistorico emh ");
		sql.append("WHERE emh.materia.id = :idMateria ");
		sql.append("ORDER BY emh.horaEstudo DESC");

		TypedQuery<EstudoMateriaHistoricoEntidade> query = this.entityManager.createQuery(sql.toString(),
				EstudoMateriaHistoricoEntidade.class);
		query.setParameter("idMateria", materia.getId());
		List<EstudoMateriaHistoricoEntidade> entidades = query.getResultList();

		List<EstudoMateriaHistorico> beans = this.usuarioEstudoMateriaHistoricoEntidadeConverter.convert(entidades);

		return beans;
	}

	@Override
	public List<ResumoMateria> buscaResumosMaterias(Estudo estudo) {
		StringBuilder sql = new StringBuilder();
		// sql.append("SELECT new
		// br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.ResumoMateriaEstudadaEntidade(
		// a.materia, a.tempoAlocado, b.tempoEstudado ) ");
		// sql.append(" FROM ( ");
		// sql.append("SELECT em.materia materia, ");
		// sql.append(" SUM( em.tempoAlocado ) tempoAlocado ");
		// sql.append(" FROM EstudoMateria em ");
		// sql.append(" WHERE em.estudo.id = :idEstudo ");
		// sql.append(" GROUP BY em.materia ) a, ( ");
		// sql.append("SELECT emh.materia materia, ");
		// sql.append(" SUM( emh.tempoEstudado ) tempoEstudado ");
		// sql.append(" FROM EstudoMateriaHistorico emh ");
		// sql.append(" WHERE emh.estudo.id = :idEstudo ");
		// sql.append(" GROUP BY emh.materia ) b ");
		// sql.append(" WHERE a.materia.id = b.materia.id ");
		// sql.append(" ORDER BY a.materia.nome ");

		sql.append("SELECT a.materia, a.tempoAlocado, b.tempoEstudado ");
		sql.append("  FROM ( ");
		sql.append("SELECT em.materia materia, ");
		sql.append("       SUM( em.tempoAlocado ) tempoAlocado ");
		sql.append("  FROM EstudoMateria em ");
		sql.append(" WHERE em.estudo = :idEstudo ");
		sql.append(" GROUP BY em.materia ) a, ( ");
		sql.append("SELECT emh.materia materia, ");
		sql.append("       SUM( emh.tempoEstudado ) tempoEstudado ");
		sql.append("  FROM EstudoMateriaHistorico emh ");
		sql.append(" WHERE emh.estudo = :idEstudo ");
		sql.append(" GROUP BY emh.materia ) b ");
		sql.append(" WHERE a.materia = b.materia ");

		Query query = this.entityManager.createNativeQuery(sql.toString());
		query.setParameter("idEstudo", estudo.getId());

		List<Object[]> entidades = query.getResultList();

		List<ResumoMateria> beans = new ArrayList<>();
		ResumoMateria bean = null;
		for (Object[] entidade : entidades) {
			bean = new ResumoMateria();

			Materia materia = new Materia();
			materia.setId(((BigInteger) entidade[0]).longValue());
			;
			bean.setMateria(materia);
			bean.setTempoAlocado(new Duration(((BigDecimal) entidade[1]).longValue()));
			bean.setSomaTempo(new Duration(((BigDecimal) entidade[2]).longValue()));

			beans.add(bean);
		}

		return beans;
	}

	@Override
	public void remove(EstudoMateriaHistorico estudoMateriaHistorico) {
		EstudoMateriaHistoricoEntidade entidade = this.usuarioEstudoMateriaHistoricoEntidadeConverter
				.convert(estudoMateriaHistorico);

		super.remove(entidade);
	}

}
