package br.com.guarasoft.studyware.usuario.casosdeuso;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;
import br.com.guarasoft.studyware.usuario.excecoes.EmailJaCadastrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

public class CadastrarUsuarioImpl implements CadastrarUsuario {

	private final UsuarioGateway usuarioGateway;

	public CadastrarUsuarioImpl(UsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	@Override
	public Boolean executar(UsuarioBean usuario) {
		UsuarioBean usuarioPesquisa = this.usuarioGateway.pesquisaPorEmail(usuario.getEmail());
		if (usuarioPesquisa != null) {
			throw new EmailJaCadastrado();
		}
		this.usuarioGateway.cadastrar(usuario);
		return Boolean.TRUE;
	}

}
