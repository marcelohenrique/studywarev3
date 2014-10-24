package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.MateriaEstudada;

public class MateriaEstudadaDaoImpl implements MateriaEstudadaDao {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Override
	public List<UsuarioEstudoMateriaHistoricoBean> findAll(
			UsuarioEstudoBean estudo) {
		List<MateriaEstudada> materiasEstudadas = this.entityManager
				.createQuery(
						"select me from MateriaEstudada me where me.estudoMateria.estudo.id = :estudo order by me.dataHoraEstudo desc",
						MateriaEstudada.class)
				.setParameter("estudo", estudo.getId()).getResultList();

		List<UsuarioEstudoMateriaHistoricoBean> masteriasEstudadasBean = new ArrayList<>();
		UsuarioEstudoMateriaHistoricoBean usuarioEstudoMateriaHistoricoBean = null;
		for (MateriaEstudada materiaEstudada : materiasEstudadas) {
			usuarioEstudoMateriaHistoricoBean = new UsuarioEstudoMateriaHistoricoBean();

			// TODO

			masteriasEstudadasBean.add(usuarioEstudoMateriaHistoricoBean);
		}

		return masteriasEstudadasBean;
	}

	@Override
	public void persist(UsuarioEstudoMateriaHistoricoBean materiaEstudada) {
		// TODO Auto-generated method stub

	}

}
