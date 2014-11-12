package br.com.guarasoft.studyware.usuario.casosdeuso;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

public class AlterarUsuarioImpl implements AlterarUsuario {

	private final UsuarioGateway usuarioGateway;

	public AlterarUsuarioImpl(UsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	@Override
	public void execute(UsuarioBean usuario) {
		this.usuarioGateway.alterar(usuario);
	}

}
