package br.com.guarasoft.studyware.estudousuario.gateway;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.guarasoft.studyware.estudousuario.excecoes.EstudoJaExiste;

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

		try {
			this.entityManager.persist(estudoUsuario);
		} catch (RuntimeException e) {
			throw new EstudoJaExiste();
		}
	}

}
