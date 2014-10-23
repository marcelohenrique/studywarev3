package br.com.guarasoft.studyware.menu.controller;

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

	public String cadastrarMateria() {
		return "/pages/materia/cadastrar";
	}

	public String consultarMateria() {
		return "/pages/materia/consultar";
	}

}