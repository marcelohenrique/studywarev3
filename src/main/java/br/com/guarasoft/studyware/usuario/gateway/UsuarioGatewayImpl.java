package br.com.guarasoft.studyware.usuario.gateway;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioServiceImpl;
import br.com.guarasoft.studyware.usuario.gateway.entidade.Usuario;

@Stateless
public class UsuarioGatewayImpl implements UsuarioGateway {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Override
	public UsuarioService pesquisaPorEmail(String email) {
		Usuario usuario = this.entityManager.find(Usuario.class, email);

		if (usuario != null) {
			return new UsuarioServiceImpl(usuario.getEmail());
		}

		return null;
	}

	@Override
	public void cadastrar(String email) {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		this.entityManager.persist(usuario);
	}

}
