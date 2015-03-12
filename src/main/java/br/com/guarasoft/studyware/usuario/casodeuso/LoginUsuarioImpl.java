package br.com.guarasoft.studyware.usuario.casodeuso;

import br.com.guarasoft.studyware.usuario.excecao.EmailNaoEncontrado;
import br.com.guarasoft.studyware.usuario.excecao.UsuarioInativo;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class LoginUsuarioImpl implements LoginUsuario {

	private final UsuarioGateway usuarioGateway;

	public LoginUsuarioImpl(UsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	@Override
	public Usuario autentica(String email) {
		Usuario usuario = this.usuarioGateway.pesquisaPorEmail(email);
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setEmail(email);
			throw new EmailNaoEncontrado(usuario);
		} else if (!usuario.isAtivo()) {
			throw new UsuarioInativo();
		}
		return usuario;
	}

}
