package br.com.guarasoft.studyware.estudomateriahistorico.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateria.gateway.converter.UsuarioEstudoMateriaBuilder;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.UsuarioEstudoMateria;
import br.com.guarasoft.studyware.estudomateriahistorico.bean.ResumoMateriaEstudadaBean;
import br.com.guarasoft.studyware.estudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.converter.UsuarioEstudoMateriaHistoricoEntidadeConverter;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.ResumoMateriaEstudada;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.UsuarioEstudoMateriaHistorico;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

@Stateless
public class UsuarioEstudoMateriaHistoricoGatewayImpl extends
		AbstractDao<UsuarioEstudoMateriaHistorico, Long> implements
		UsuarioEstudoMateriaHistoricoGateway {

	private final UsuarioEstudoMateriaBuilder usuarioEstudoMateriaBuilder = new UsuarioEstudoMateriaBuilder();
	private final UsuarioEstudoMateriaHistoricoEntidadeConverter usuarioEstudoMateriaHistoricoEntidadeConverter = new UsuarioEstudoMateriaHistoricoEntidadeConverter();

	public UsuarioEstudoMateriaHistoricoGatewayImpl() {
		super(UsuarioEstudoMateriaHistorico.class);
	}

	@Override
	public void persist(UsuarioEstudoMateriaHistoricoBean materiaEstudada) {
		UsuarioEstudoMateriaHistorico entidade = this.usuarioEstudoMateriaHistoricoEntidadeConverter
				.convert(materiaEstudada);

		this.persist(entidade);
	}

	@Override
	public void merge(UsuarioEstudoMateriaHistoricoBean bean) {
		UsuarioEstudoMateriaHistorico entidade = this.usuarioEstudoMateriaHistoricoEntidadeConverter
				.convert(bean);

		this.merge(entidade);
	}

	@Override
	public List<UsuarioEstudoMateriaHistoricoBean> findAll(
			Estudo estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" FROM UsuarioEstudoMateriaHistorico uemh ");
		sql.append("WHERE uemh.usuarioEstudoMateria.estudo.nome = :nomeEstudo ");
		sql.append("ORDER BY uemh.horaEstudo DESC");

		TypedQuery<UsuarioEstudoMateriaHistorico> query = this.entityManager
				.createQuery(sql.toString(),
						UsuarioEstudoMateriaHistorico.class);
		query.setParameter("nomeEstudo", estudo.getNome());
		List<UsuarioEstudoMateriaHistorico> entidades = query.getResultList();

		List<UsuarioEstudoMateriaHistoricoBean> beans = this.usuarioEstudoMateriaHistoricoEntidadeConverter
				.convert(estudo, entidades);

		return beans;
	}

	@Override
	public List<ResumoMateriaEstudadaBean> buscaResumosMaterias(
			Estudo estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.ResumoMateriaEstudada( uem, SUM( uemh.tempoEstudado ), '' ) ");
		sql.append("  FROM UsuarioEstudoMateria uem ");
		sql.append("  LEFT JOIN uem.historico uemh ");
		sql.append(" WHERE uem.estudo.nome = :nomeEstudo ");
		sql.append(" GROUP BY uem ");
		sql.append(" ORDER BY uem.ordem");

		TypedQuery<ResumoMateriaEstudada> query = this.entityManager
				.createQuery(sql.toString(), ResumoMateriaEstudada.class);
		query.setParameter("nomeEstudo", estudo.getNome());

		List<ResumoMateriaEstudada> entidades = query.getResultList();

		List<ResumoMateriaEstudadaBean> beans = new ArrayList<>();
		ResumoMateriaEstudadaBean bean = null;
		for (ResumoMateriaEstudada entidade : entidades) {
			bean = new ResumoMateriaEstudadaBean();

			bean.setUsuarioEstudoMateria(this.usuarioEstudoMateriaBuilder
					.convert(estudo,
							entidade.getUsuarioEstudoMateria()));
			bean.setSomaTempo(new Duration(entidade.getSomaTempo()));
			bean.setObservacao(this.buscaUltimaObservacao(entidade
					.getUsuarioEstudoMateria()));

			beans.add(bean);
		}

		return beans;
	}

	private String buscaUltimaObservacao(
			UsuarioEstudoMateria usuarioEstudoMateria) {
		StringBuilder sql = new StringBuilder();
		// sql.append("SELECT uemh.observacao ");
		sql.append("  FROM UsuarioEstudoMateriaHistorico uemh ");
		sql.append(" WHERE uemh.id IN ( ");
		sql.append("SELECT MAX( h.id ) ");
		sql.append("  FROM UsuarioEstudoMateriaHistorico h ");
		sql.append(" WHERE h.usuarioEstudoMateria.id = :usuarioEstudoMateria ");
		sql.append(" GROUP BY h.usuarioEstudoMateria ) ");

		TypedQuery<UsuarioEstudoMateriaHistorico> query = this.entityManager
				.createQuery(sql.toString(),
						UsuarioEstudoMateriaHistorico.class);
		query.setParameter("usuarioEstudoMateria", usuarioEstudoMateria.getId());

		try {
			return query.getSingleResult().getObservacao();
		} catch (NoResultException e) {
			return "";
		}
	}
}
