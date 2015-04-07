package br.com.guarasoft.studyware.menu.controller;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "controller")
public class MenuController {

	public String home() {
		return "/pages/main";
	}

	public String cadastrarEstudo() {
		return "/pages/usuarioestudo/cadastrar";
	}

	public String consultarUsuarioEstudo() {
		return "/pages/usuarioestudo/consultar";
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
