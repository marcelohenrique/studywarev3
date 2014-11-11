package br.com.guarasoft.studyware.usuario.excecoes;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;

public class EmailNaoEncontrado extends RuntimeException {

	private static final long serialVersionUID = 6887718198520467631L;

	private final UsuarioBean usuario;

	public EmailNaoEncontrado(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public UsuarioBean getUsuario() {
		return this.usuario;
	}

}
