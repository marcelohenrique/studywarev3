package br.com.guarasoft.studyware.usuario.casosdeuso;

import br.com.guarasoft.studyware.usuario.entidades.Usuario;
import br.com.guarasoft.studyware.usuario.excecoes.EmailNaoEncontrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

public class LoginUsuarioImpl implements LoginUsuario {

	private UsuarioGateway usuarioGateway;

	public LoginUsuarioImpl(UsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	@Override
	public Usuario autenticar(String email) {
		Usuario usuario = usuarioGateway.pesquisaPorEmail(email);
		if (usuario == null) {
			throw new EmailNaoEncontrado();
		}
		return usuario;
	}

}
