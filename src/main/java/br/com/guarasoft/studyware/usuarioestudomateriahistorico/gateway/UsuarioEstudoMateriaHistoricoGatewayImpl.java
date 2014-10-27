package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter.UsuarioEstudoMateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.ResumoMateriaEstudadaBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.converter.UsuarioEstudoMateriaHistoricoEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.ResumoMateriaEstudada;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.UsuarioEstudoMateriaHistorico;

public class UsuarioEstudoMateriaHistoricoGatewayImpl extends
		AbstractDao<UsuarioEstudoMateriaHistorico, Long> implements
		UsuarioEstudoMateriaHistoricoGateway {

	@Inject
	private UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter;
	@Inject
	private UsuarioEstudoMateriaHistoricoEntidadeConverter usuarioEstudoMateriaHistoricoEntidadeConverter;

	public UsuarioEstudoMateriaHistoricoGatewayImpl() {
		super(UsuarioEstudoMateriaHistorico.class);
	}

	@Override
	public void persist(UsuarioEstudoMateriaHistoricoBean materiaEstudada) {
		UsuarioEstudoMateriaHistorico entidade = this.usuarioEstudoMateriaHistoricoEntidadeConverter
				.convert(materiaEstudada);

		persist(entidade);
	}

	@Override
	public List<UsuarioEstudoMateriaHistoricoBean> findAll(
			UsuarioEstudoBean estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" FROM UsuarioEstudoMateriaHistorico uemh ");
		sql.append("WHERE uemh.usuarioEstudoMateria.usuarioEstudoMateriaPK.usuarioEstudo.id = :usuarioEstudo ");
		sql.append("ORDER BY uemh.horaEstudo DESC");

		List<UsuarioEstudoMateriaHistorico> entidades = this.entityManager
				.createQuery(sql.toString(),
						UsuarioEstudoMateriaHistorico.class)
				.setParameter("usuarioEstudo", estudo.getId()).getResultList();

		List<UsuarioEstudoMateriaHistoricoBean> beans = new ArrayList<>();
		UsuarioEstudoMateriaHistoricoBean bean = null;
		for (UsuarioEstudoMateriaHistorico entidade : entidades) {
			bean = new UsuarioEstudoMateriaHistoricoBean();

			bean.setId(entidade.getId());
			bean.setUsuarioEstudoMateria(this.usuarioEstudoMateriaEntidadeConverter
					.convert(entidade.getUsuarioEstudoMateria()));

			beans.add(bean);
		}

		return beans;
	}

	@Override
	public List<ResumoMateriaEstudadaBean> buscaResumosMaterias(
			UsuarioEstudoBean usuarioEstudoBean) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.ResumoMateriaEstudada( uem, SUM( uemh.tempoEstudado ) ) ");
		sql.append("  FROM UsuarioEstudoMateriaHistorico uemh ");
		sql.append("  LEFT OUTER JOIN uemh.usuarioEstudoMateria uem ");
		sql.append(" WHERE uem.usuarioEstudoMateriaPK.usuarioEstudo.id = :usuarioEstudo ");
		sql.append(" GROUP BY uem.usuarioEstudoMateriaPK.usuarioEstudo, ");
		sql.append("       uem.usuarioEstudoMateriaPK.materia ");
		sql.append(" ORDER BY uem.ordem ");

		Query query = this.entityManager.createQuery(sql.toString(),
				ResumoMateriaEstudada.class);
		query.setParameter("usuarioEstudo", usuarioEstudoBean.getId());

		List<ResumoMateriaEstudada> entidades = query.getResultList();

		List<ResumoMateriaEstudadaBean> beans = new ArrayList<>();
		ResumoMateriaEstudadaBean bean = null;
		for (ResumoMateriaEstudada entidade : entidades) {
			bean = new ResumoMateriaEstudadaBean();
			bean.setUsuarioEstudoMateria(this.usuarioEstudoMateriaEntidadeConverter
					.convert(entidade.getUsuarioEstudoMateria()));
			bean.setSomaTempo(new Duration(entidade.getSomaTempo()));

			beans.add(bean);
		}

		return beans;
	}

}
