package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter.UsuarioEstudoMateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.ResumoMateriaEstudadaBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.converter.UsuarioEstudoMateriaHistoricoEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.ResumoMateriaEstudada;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.UsuarioEstudoMateriaHistorico;

public class UsuarioEstudoMateriaHistoricoGatewayImpl extends AbstractDao<UsuarioEstudoMateriaHistorico, Long> implements UsuarioEstudoMateriaHistoricoGateway {

	@Inject
	private UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter;
	@Inject
	private UsuarioEstudoMateriaHistoricoEntidadeConverter usuarioEstudoMateriaHistoricoEntidadeConverter;

	public UsuarioEstudoMateriaHistoricoGatewayImpl() {
		super(UsuarioEstudoMateriaHistorico.class);
	}

	@Override
	public void persist(UsuarioEstudoMateriaHistoricoBean materiaEstudada) {
		UsuarioEstudoMateriaHistorico entidade = this.usuarioEstudoMateriaHistoricoEntidadeConverter.convert(materiaEstudada);

		persist(entidade);
	}

	@Override
	public List<UsuarioEstudoMateriaHistoricoBean> findAll(UsuarioEstudoBean estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" FROM UsuarioEstudoMateriaHistorico uemh ");
		sql.append("WHERE uemh.usuarioEstudoMateria.pk.usuarioEstudo.id = :usuarioEstudo ");
		sql.append("ORDER BY uemh.horaEstudo DESC");

		TypedQuery<UsuarioEstudoMateriaHistorico> query = this.entityManager.createQuery(sql.toString(), UsuarioEstudoMateriaHistorico.class);
		query.setParameter("usuarioEstudo", estudo.getId());
		List<UsuarioEstudoMateriaHistorico> entidades = query.getResultList();

		List<UsuarioEstudoMateriaHistoricoBean> beans = this.usuarioEstudoMateriaHistoricoEntidadeConverter.convert(estudo, entidades);

		return beans;
	}

	@Override
	public List<ResumoMateriaEstudadaBean> buscaResumosMaterias(UsuarioEstudoBean usuarioEstudoBean) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT new br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.ResumoMateriaEstudada( uem, SUM( uemh.tempoEstudado ) ) ");
		sql.append("  FROM UsuarioEstudoMateriaHistorico uemh ");
		sql.append("  LEFT OUTER JOIN uemh.usuarioEstudoMateria uem ");
		sql.append(" WHERE uem.pk.usuarioEstudo.id = :usuarioEstudo ");
		sql.append(" GROUP BY uem.pk.usuarioEstudo, ");
		sql.append("       uem.pk.materia ");
		sql.append(" ORDER BY uem.ordem ");

		Query query = this.entityManager.createQuery(sql.toString(), ResumoMateriaEstudada.class);
		query.setParameter("usuarioEstudo", usuarioEstudoBean.getId());

		List<ResumoMateriaEstudada> entidades = query.getResultList();

		List<ResumoMateriaEstudadaBean> beans = new ArrayList<>();
		ResumoMateriaEstudadaBean bean = null;
		for (ResumoMateriaEstudada entidade : entidades) {
			bean = new ResumoMateriaEstudadaBean();
			bean.setUsuarioEstudoMateria(this.usuarioEstudoMateriaEntidadeConverter.convert(usuarioEstudoBean, entidade.getUsuarioEstudoMateria()));
			bean.setSomaTempo(new Duration(entidade.getSomaTempo()));

			beans.add(bean);
		}

		return beans;
	}

}
