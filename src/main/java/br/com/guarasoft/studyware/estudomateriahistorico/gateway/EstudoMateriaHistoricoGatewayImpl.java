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

			beans.add(bean);
		}

		return beans;
	}

}
