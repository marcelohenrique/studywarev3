package br.com.guarasoft.studyware.acesso.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.guarasoft.studyware.usuario.modelo.Usuario;
import lombok.Data;

@Named("sessionAuth")
@SessionScoped
@Data
public class SessionAuth implements Serializable {

	private static final long serialVersionUID = -4516153840248949753L;

	private String token;
	private Usuario usuario;

}
