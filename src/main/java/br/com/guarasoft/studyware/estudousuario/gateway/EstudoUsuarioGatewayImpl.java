package br.com.guarasoft.studyware.estudousuario.gateway;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;
import br.com.guarasoft.studyware.estudousuario.gateway.entidade.EstudoUsuario;

@Stateless
public class EstudoUsuarioGatewayImpl implements EstudoUsuarioGateway {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Override
	public void cadastrar(String email, String nomeEstudo, Date fim) {
		EstudoUsuario estudoUsuario = new EstudoUsuario();
		estudoUsuario.setEmail(email);
		estudoUsuario.setNome(nomeEstudo);
		estudoUsuario.setFim(fim);

		this.entityManager.persist(estudoUsuario);
	}

	@Override
	public List<EstudoUsuarioBean> recuperaEstudos(String email) {
		TypedQuery<EstudoUsuario> typedQuery = this.entityManager
				.createQuery(
						"from EstudoUsuario eu where eu.email = :email",
						EstudoUsuario.class);

		typedQuery.setParameter("email", email);

		List<EstudoUsuario> estudosUsuario = typedQuery.getResultList();

		List<EstudoUsuarioBean> estudos = converteEstudoUsuarioParaEstudoUsuarioBean(estudosUsuario);

		return estudos;
	}

	private List<EstudoUsuarioBean> converteEstudoUsuarioParaEstudoUsuarioBean(
			List<EstudoUsuario> estudosUsuario) {
		List<EstudoUsuarioBean> estudos = new ArrayList<>();
		EstudoUsuarioBean estudoUsuarioBean = null;
		for (EstudoUsuario estudoUsuario : estudosUsuario) {
			estudoUsuarioBean = converteEstudoUsuarioParaBean(estudoUsuario);

			estudos.add(estudoUsuarioBean);
		}
		return estudos;
	}

	private EstudoUsuarioBean converteEstudoUsuarioParaBean(
			EstudoUsuario estudoUsuario) {
		EstudoUsuarioBean estudoUsuarioBean = new EstudoUsuarioBean();
		estudoUsuarioBean.setId(estudoUsuario.getId());
		estudoUsuarioBean.setEmail(estudoUsuario.getEmail());
		estudoUsuarioBean.setNome(estudoUsuario.getNome());
		estudoUsuarioBean.setFim(estudoUsuario.getFim());
		return estudoUsuarioBean;
	}
}
