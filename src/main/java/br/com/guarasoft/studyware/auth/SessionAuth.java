package br.com.guarasoft.studyware.auth;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;

@ManagedBean(name = "sessionAuth")
@SessionScoped
public class SessionAuth {

	private String token;
	private UsuarioService usuario;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsuarioService getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioService usuario) {
		this.usuario = usuario;
	}

}
