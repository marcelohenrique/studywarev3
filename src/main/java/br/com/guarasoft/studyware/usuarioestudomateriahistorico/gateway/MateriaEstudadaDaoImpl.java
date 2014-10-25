package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter.UsuarioEstudoMateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.UsuarioEstudoMateriaHistorico;

public class MateriaEstudadaDaoImpl implements MateriaEstudadaDao {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Inject
	private UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter;

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
	public void persist(UsuarioEstudoMateriaHistoricoBean materiaEstudada) {
		// TODO Auto-generated method stub

	}

}
