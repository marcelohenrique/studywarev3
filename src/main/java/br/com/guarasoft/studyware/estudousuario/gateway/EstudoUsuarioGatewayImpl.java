package br.com.guarasoft.studyware.estudousuario.gateway;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;
import br.com.guarasoft.studyware.estudousuario.gateway.entidade.EstudoUsuario;
import br.com.guarasoft.studyware.estudousuario.gateway.entidade.EstudoUsuarioPK;

@Stateless
public class EstudoUsuarioGatewayImpl implements EstudoUsuarioGateway {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Override
	public void cadastrar(String email, String nomeEstudo, Date fim) {
		EstudoUsuarioPK estudoUsuarioPK = new EstudoUsuarioPK();
		estudoUsuarioPK.setEmail(email);
		estudoUsuarioPK.setNome(nomeEstudo);

		EstudoUsuario estudoUsuario = new EstudoUsuario();
		estudoUsuario.setEstudoUsuarioPK(estudoUsuarioPK);
		estudoUsuario.setFim(fim);

		this.entityManager.persist(estudoUsuario);
	}

	@Override
	public List<EstudoUsuarioBean> recuperaEstudos(String email) {

		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

		CriteriaQuery<EstudoUsuario> cq = cb.createQuery(EstudoUsuario.class);

		Root<EstudoUsuario> root = cq.from(EstudoUsuario.class);

		cq.where(cb.equal(root.get("estudoUsuarioPK").get("email"), cb.parameter(String.class, "email")));

		TypedQuery<EstudoUsuario> tq = this.entityManager.createQuery(cq);

		tq.setParameter("email", email);

		List<EstudoUsuario> estudosUsuario = tq.getResultList();

		List<EstudoUsuarioBean> estudos = converteEstudoUsuarioParaEstudoUsuarioBean(estudosUsuario);

		return estudos;
	}

	private List<EstudoUsuarioBean> converteEstudoUsuarioParaEstudoUsuarioBean(List<EstudoUsuario> estudosUsuario) {
		List<EstudoUsuarioBean> estudos = new ArrayList<>();
		EstudoUsuarioBean estudoUsuarioBean = null;
		for (EstudoUsuario estudoUsuario : estudosUsuario) {
			estudoUsuarioBean = new EstudoUsuarioBean();
			estudoUsuarioBean.setNome(estudoUsuario.getEstudoUsuarioPK().getNome());
			estudoUsuarioBean.setFim(estudoUsuario.getFim());

			estudos.add(estudoUsuarioBean);
		}
		return estudos;
	}
}
