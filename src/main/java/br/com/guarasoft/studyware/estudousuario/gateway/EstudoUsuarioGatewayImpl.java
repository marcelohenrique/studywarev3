package br.com.guarasoft.studyware.estudousuario.gateway;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstudoUsuarioGatewayImpl implements EstudoUsuarioGateway {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Override
	public void cadastrar(String email, String nomeEstudo, Date fim) {
		EstudoUsuarioPK estudoUsuarioPK = new EstudoUsuarioPK();
		estudoUsuarioPK.setEmail(email);

		EstudoUsuario estudoUsuario = new EstudoUsuario();
		estudoUsuario.setEstudodoEstudoUsuarioPK(estudoUsuarioPK);
		estudoUsuario.setNome(nomeEstudo);
		estudoUsuario.setFim(fim);

		this.entityManager.persist(estudoUsuario);
	}

}
