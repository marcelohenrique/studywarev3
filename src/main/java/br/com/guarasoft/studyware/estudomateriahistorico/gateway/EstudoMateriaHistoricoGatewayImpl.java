package br.com.guarasoft.studyware.estudomateriahistorico.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.converter.UsuarioEstudoMateriaHistoricoEntidadeConverter;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.EstudoMateriaHistoricoEntidade;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.ResumoMateriaEstudadaEntidade;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.EstudoMateriaHistorico;
import br.com.guarasoft.studyware.estudomateriahistorico.modelo.ResumoMateria;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;

@Stateless
public class EstudoMateriaHistoricoGatewayImpl extends
		AbstractDao<EstudoMateriaHistoricoEntidade, Long> implements
		EstudoMateriaHistoricoGateway {

	// private final UsuarioEstudoMateriaBuilder usuarioEstudoMateriaBuilder =
	// new UsuarioEstudoMateriaBuilder();
	private final UsuarioEstudoMateriaHistoricoEntidadeConverter usuarioEstudoMateriaHistoricoEntidadeConverter = new UsuarioEstudoMateriaHistoricoEntidadeConverter();
	private final MateriaEntidadeConverter materiaEntidadeConverter = new MateriaEntidadeConverter();

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
		EstudoMateriaHistoricoEntidade entidade = this.usuarioEstudoMateriaHistoricoEntidadeConverter
				.convert(bean);

		this.merge(entidade);
	}

	@Override
	public List<EstudoMateriaHistorico> findAll(Estudo estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT emh ");
		sql.append(" FROM EstudoMateriaHistorico emh ");
		sql.append("WHERE emh.estudo.id = :idEstudo ");
		sql.append("ORDER BY emh.horaEstudo DESC");

		TypedQuery<EstudoMateriaHistoricoEntidade> query = this.entityManager
				.createQuery(sql.toString(),
						EstudoMateriaHistoricoEntidade.class);
		query.setParameter("idEstudo", estudo.getId());
		List<EstudoMateriaHistoricoEntidade> entidades = query.getResultList();

		List<EstudoMateriaHistorico> beans = this.usuarioEstudoMateriaHistoricoEntidadeConverter
				.convert(estudo, entidades);

		return beans;
	}

	@Override
	public List<ResumoMateria> buscaResumosMaterias(Estudo estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.ResumoMateriaEstudadaEntidade( emh.materia, SUM( emh.tempoEstudado ) ) ");
		sql.append("  FROM EstudoMateriaHistorico emh ");
		sql.append(" WHERE emh.estudo.id = :idEstudo ");
		sql.append(" GROUP BY emh.materia ");
		// sql.append(" ORDER BY emh.ordem");
		// sql.append("SELECT new br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.ResumoMateriaEstudadaEntidade( a.materia, a.tempoEstudado, b.qtd, '' ) ");
		// sql.append("  FROM ( ");
		// sql.append("SELECT emh.materia materia, ");
		// sql.append("       SUM( emh.tempoEstudado ) tempoEstudado ");
		// sql.append("  FROM EstudoMateriaHistorico emh ");
		// sql.append(" WHERE emh.estudo.id = :idEstudo ");
		// sql.append(" GROUP BY emh.materia ) a, ( ");
		// sql.append("SELECT em.materia materia, ");
		// sql.append("       COUNT( em.materia ) qtd ");
		// sql.append("  FROM EstudoMateria em ");
		// sql.append(" WHERE em.estudo.id = :idEstudo ");
		// sql.append(" GROUP BY em.materia ) b ");
		// sql.append(" WHERE a.materia = b.materia");
		// sql.append(" ORDER BY emh.materia.nome");

		TypedQuery<ResumoMateriaEstudadaEntidade> query = this.entityManager
				.createQuery(sql.toString(),
						ResumoMateriaEstudadaEntidade.class);
		query.setParameter("idEstudo", estudo.getId());

		List<ResumoMateriaEstudadaEntidade> entidades = query.getResultList();

		List<ResumoMateria> beans = new ArrayList<>();
		ResumoMateria bean = null;
		for (ResumoMateriaEstudadaEntidade entidade : entidades) {
			bean = new ResumoMateria();

			bean.setMateria(this.materiaEntidadeConverter.convert(entidade
					.getMateria()));
			bean.setSomaTempo(new Duration(entidade.getSomaTempo()));
			// bean.setQtd(entidade.getQtd());
			// bean.setObservacao(this.buscaUltimaObservacao(entidade.getMateria()));

			beans.add(bean);
		}

		return beans;
	}

	// private String buscaUltimaObservacao(MateriaEntidade materiaEntidade) {
	// // StringBuilder sql = new StringBuilder();
	// // sql.append("SELECT emhe ");
	// // sql.append("  FROM EstudoMateriaHistorico emh ");
	// // sql.append(" WHERE emh.id IN ( ");
	// // sql.append("SELECT MAX( h.id ) ");
	// // sql.append("  FROM EstudoMateriaHistorico h ");
	// // sql.append(" WHERE 1 = 1 ");
	// // sql.append("   AND h.estudo = :idEstudo ");
	// // sql.append("   AND h.materia = :idMateria ");
	// // sql.append(" GROUP BY h.materia ) ");
	// //
	// // TypedQuery<EstudoMateriaHistoricoEntidade> query = this.entityManager
	// // .createQuery(sql.toString(),
	// // EstudoMateriaHistoricoEntidade.class);
	// // query.setParameter("estudoMateria", materiaEntidade.getId());
	// //
	// // try {
	// // return query.getSingleResult().getObservacao();
	// // } catch (NoResultException e) {
	// // return "";
	// // }
	// return "";
	// }
}
