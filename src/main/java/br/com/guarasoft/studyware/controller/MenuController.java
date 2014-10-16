package br.com.guarasoft.studyware.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "controller")
public class MenuController {

	public String cadastrarUsuario() {
		return "usuario/cadastrar";
	}

	public String cadastrarEstudoUsuario() {
		return "estudousuario/cadastrar";
	}

}
