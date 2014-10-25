package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;

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
	public List<UsuarioEstudoMateriaHistoricoBean> findAll(
			UsuarioEstudoBean estudo) {
		List<UsuarioEstudoMateriaHistorico> entidades = this.entityManager
				.createQuery(
						"select me from UsuarioEstudoMateriaHistorico me where me.estudoMateria.estudo.id = :estudo order by me.dataHoraEstudo desc",
						UsuarioEstudoMateriaHistorico.class)
				.setParameter("estudo", estudo.getId()).getResultList();

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
		sql.append("SELECT uem.id, ");
		sql.append("       SUM( uemh.tempoEstudade ) somaTempo, ");
		sql.append("       m.nome ");
		sql.append("  FROM UsuarioEstudoMateriaHistorico uemh ");
		sql.append(" RIGHT OUTER JOIN UsuarioEstudoMateria uem ");
		sql.append("    ON uemh.usuarioEstudo = uem.usuarioEstudo ");
		sql.append("   AND uemh.materia = uem.materia ");
		sql.append("  JOIN Materia m ");
		sql.append("    ON uem.materia = m.id ");
		sql.append(" WHERE uem.usuarioEstudo = ? ");
		sql.append(" GROUP BY uem.usuarioEstudo, ");
		sql.append("       uem.materia ");
		sql.append(" ORDER BY uem.ordem ");

		Query query = this.entityManager.createNativeQuery(sql.toString(),
				ResumoMateriaEstudada.class);
		query.setParameter(1, usuarioEstudoBean.getId());

		List<ResumoMateriaEstudada> resumo = query.getResultList();

		List<ResumoMateriaEstudadaBean> resumosBean = new ArrayList<>();

		return resumosBean;
	}

	@Override
	public void persist(UsuarioEstudoMateriaHistoricoBean materiaEstudada) {
		UsuarioEstudoMateriaHistorico entidade = this.usuarioEstudoMateriaHistoricoEntidadeConverter
				.convert(materiaEstudada);

		persist(entidade);
	}

}
