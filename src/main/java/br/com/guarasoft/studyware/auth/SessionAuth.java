package br.com.guarasoft.studyware.auth;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Data;
import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;

@ManagedBean(name = "sessionAuth")
@SessionScoped
@Data
public class SessionAuth implements Serializable {

	private static final long serialVersionUID = -4516153840248949753L;

	private String token;
	private UsuarioBean usuario;

}
