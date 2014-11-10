package br.com.guarasoft.studyware.usuario.casosdeuso;

import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;
import br.com.guarasoft.studyware.usuario.excecoes.EmailJaCadastrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

public class CadastrarUsuarioImpl implements CadastrarUsuario {

	private final UsuarioGateway usuarioGateway;

	public CadastrarUsuarioImpl(UsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	@Override
	public Boolean executar(String email) {
		UsuarioService usuarioService = this.usuarioGateway.pesquisaPorEmail(email);
		if (usuarioService != null) {
			throw new EmailJaCadastrado();
		}
		this.usuarioGateway.cadastrar(email);
		return Boolean.TRUE;
	}

}
