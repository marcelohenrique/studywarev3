package br.com.guarasoft.studyware.usuario.gateway;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;
import br.com.guarasoft.studyware.usuario.gateway.entidade.Usuario;

@Stateless
public class UsuarioGatewayImpl implements UsuarioGateway {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Override
	public UsuarioBean pesquisaPorEmail(String email) {
		Usuario usuario = this.entityManager.find(Usuario.class, email);

		if (usuario != null) {
			UsuarioBean bean = new UsuarioBean();
			bean.setEmail(usuario.getEmail());
			bean.setAtivo(usuario.getAtivo());
			return bean;
		}

		return null;
	}

	@Override
	public void cadastrar(UsuarioBean usuario) {
		Usuario entidade = new Usuario();
		entidade.setEmail(usuario.getEmail());
		entidade.setAtivo(usuario.isAtivo());
		this.entityManager.persist(entidade);
	}

}
