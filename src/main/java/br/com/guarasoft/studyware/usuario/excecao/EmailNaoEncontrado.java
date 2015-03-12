package br.com.guarasoft.studyware.usuario.excecao;

import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class EmailNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 6887718198520467631L;

	private final Usuario usuario;

	public EmailNaoEncontrado(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

}
