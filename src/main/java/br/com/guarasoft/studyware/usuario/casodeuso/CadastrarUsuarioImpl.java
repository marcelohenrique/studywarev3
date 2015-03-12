package br.com.guarasoft.studyware.usuario.casodeuso;

import br.com.guarasoft.studyware.usuario.excecao.EmailJaCadastrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class CadastrarUsuarioImpl implements CadastrarUsuario {

	private final UsuarioGateway usuarioGateway;

	public CadastrarUsuarioImpl(UsuarioGateway usuarioGateway) {
		this.usuarioGateway = usuarioGateway;
	}

	@Override
	public Boolean executar(Usuario usuario) {
		Usuario usuarioPesquisa = this.usuarioGateway.pesquisaPorEmail(usuario.getEmail());
		if (usuarioPesquisa != null) {
			throw new EmailJaCadastrado();
		}
		this.usuarioGateway.cadastrar(usuario);
		return Boolean.TRUE;
	}

}
