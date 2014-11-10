package br.com.guarasoft.studyware.usuario.casosdeuso;

import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;
import br.com.guarasoft.studyware.usuario.excecoes.EmailNaoEncontrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

public class LoginUsuarioImpl implements LoginUsuario {

	private final UsuarioGateway usuarioGateway;

	public LoginUsuarioImpl(UsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	@Override
	public UsuarioService autenticar(String email) {
		UsuarioService usuarioService = this.usuarioGateway.pesquisaPorEmail(email);
		if (usuarioService == null) {
			throw new EmailNaoEncontrado();
		}
		return usuarioService;
	}

}
