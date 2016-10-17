package br.com.guarasoft.studyware.menu.controller;

import javax.inject.Named;

@Named("controller")
public class MenuController {

	public String home() {
		return "/pages/main";
	}

	public String cadastrarEstudo() {
		return "/pages/estudo/cadastrar";
	}

	public String consultarUsuarioEstudo() {
		return "/pages/estudo/consultar";
	}

	public String cadastrarUsuario() {
		return "/pages/usuario/cadastrar";
	}

	public String consultarUsuario() {
		return "/pages/usuario/consultar";
	}

	public String cadastrarMateria() {
		return "/pages/materia/cadastrar";
	}

	public String consultarMateria() {
		return "/pages/materia/consultar";
	}

}
