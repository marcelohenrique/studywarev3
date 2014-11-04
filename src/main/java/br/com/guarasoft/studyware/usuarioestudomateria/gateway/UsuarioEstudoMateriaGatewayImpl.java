package br.com.guarasoft.studyware.usuarioestudomateria.gateway;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter.UsuarioEstudoMateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

public class UsuarioEstudoMateriaGatewayImpl extends AbstractDao<UsuarioEstudoMateria, Long> implements UsuarioEstudoMateriaGateway {

	public UsuarioEstudoMateriaGatewayImpl() {
		super(UsuarioEstudoMateria.class);
	}

	@Inject
	private UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter;

	@Override
	public UsuarioEstudoMateriaBean find(UsuarioEstudoBean usuarioEstudoBean, MateriaBean materiaBean) {
		StringBuilder sql = new StringBuilder();
		sql.append(" FROM UsuarioEstudoMateria uem ");
		sql.append("WHERE uem.usuarioEstudo.id = :usuarioEstudo ");
		sql.append("  AND uem.materia.id = :materia ");

		TypedQuery<UsuarioEstudoMateria> query = this.entityManager.createQuery(sql.toString(), UsuarioEstudoMateria.class);

		query.setParameter("usuarioEstudo", usuarioEstudoBean.getId());
		query.setParameter("materia", materiaBean.getId());

		UsuarioEstudoMateria entidade = query.getSingleResult();

		UsuarioEstudoMateriaBean bean = this.usuarioEstudoMateriaEntidadeConverter.convert(usuarioEstudoBean, entidade);

		return bean;
	}

	@Override
	public List<UsuarioEstudoMateriaBean> buscaPorUsuarioEstudo(UsuarioEstudoBean usuarioEstudoBean) {
		StringBuilder sql = new StringBuilder();
		sql.append(" from UsuarioEstudoMateria uem ");
		sql.append("where uem.usuarioEstudo.id = :usuarioEstudo");

		Query query = this.entityManager.createQuery(sql.toString(), UsuarioEstudoMateria.class);
		query.setParameter("usuarioEstudo", usuarioEstudoBean.getId());
		List<UsuarioEstudoMateria> entidades = query.getResultList();

		List<UsuarioEstudoMateriaBean> beans = this.usuarioEstudoMateriaEntidadeConverter.convert(usuarioEstudoBean, entidades);

		return beans;
	}

	// @Override
	// public List<UsuarioEstudoMateriaBean> buscaTodos(UsuarioEstudoBean
	// usuarioEstudoBean) {
	// StringBuilder sql = new StringBuilder();
	// // sql.append("FROM Materia m ");
	// // sql.append("  LEFT OUTER JOIN m.usuarioEstudoMaterias uem ");
	// // sql.append("  WITH uem.pk.usuarioEstudo.id = :usuarioEstudo ");
	// sql.append("SELECT * FROM MATERIA M ");
	// sql.append("  LEFT JOIN USUARIOESTUDOMATERIA UEM ");
	// sql.append("    ON M.ID = UEM.MATERIA ");
	// sql.append("   AND UEM.USUARIOESTUDO = 1001 ");
	//
	// // Query query = this.entityManager.createQuery(sql.toString(),
	// // UsuarioEstudoMateria.class);
	// Query query = this.entityManager.createNativeQuery(sql.toString(),
	// UsuarioEstudoMateria.class);
	// // query.setParameter("usuarioEstudo", usuarioEstudoBean.getId());
	// List<UsuarioEstudoMateria> entidades = query.getResultList();
	//
	// List<UsuarioEstudoMateriaBean> beans =
	// this.usuarioEstudoMateriaEntidadeConverter.convert(usuarioEstudoBean,
	// entidades);
	//
	// return beans;
	// }
}