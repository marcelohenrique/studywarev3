package br.com.guarasoft.studyware.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "controller")
public class MenuController {

	public String home() {
		return "/pages/main";
	}

	public String cadastrarUsuario() {
		return "/pages/usuario/cadastrar";
	}

	public String cadastrarEstudoUsuario() {
		return "/pages/estudousuario/cadastrar";
	}

}
