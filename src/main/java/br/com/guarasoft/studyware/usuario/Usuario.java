package br.com.guarasoft.studyware.usuario;

import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

public class Usuario {

	public Usuario(UsuarioGateway usuarioGateway, String email) {
	}

	public Boolean autenticar() {
		return Boolean.TRUE;
	}

}
