package br.com.guarasoft.studyware.usuario.casodeuso;

import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class AlterarUsuarioImpl implements AlterarUsuario {

	private final UsuarioGateway usuarioGateway;

	public AlterarUsuarioImpl(UsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	@Override
	public void execute(Usuario usuario) {
		this.usuarioGateway.alterar(usuario);
	}

}
