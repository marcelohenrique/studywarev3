package br.com.guarasoft.studyware.usuario.casosdeuso;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;
import br.com.guarasoft.studyware.usuario.excecao.UsuarioInativo;
import br.com.guarasoft.studyware.usuario.excecoes.EmailNaoEncontrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

public class LoginUsuarioImpl implements LoginUsuario {

	private final UsuarioGateway usuarioGateway;

	public LoginUsuarioImpl(UsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	@Override
	public UsuarioBean autentica(String email) {
		UsuarioBean usuario = this.usuarioGateway.pesquisaPorEmail(email);
		if (usuario == null) {
			throw new EmailNaoEncontrado();
		} else if (!usuario.isAtivo()) {
			throw new UsuarioInativo();
		}
		return usuario;
	}

}
