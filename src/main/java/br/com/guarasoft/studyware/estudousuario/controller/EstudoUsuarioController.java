package br.com.guarasoft.studyware.estudousuario.controller;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.guarasoft.studyware.estudousuario.casosdeuso.CadastrarEstudoUsuario;
import br.com.guarasoft.studyware.estudousuario.casosdeuso.CadastrarEstudoUsuarioImpl;
import br.com.guarasoft.studyware.estudousuario.gateway.EstudoUsuarioGateway;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;

@ManagedBean(name = "estudoUsuario")
public class EstudoUsuarioController {

	private FacesContext context = FacesContext.getCurrentInstance();

	@Inject
	private EstudoUsuarioGateway estudoUsuarioGateway;
	private CadastrarEstudoUsuario cadastrarEstudoUsuario;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	private UsuarioService usuarioService;

	private String nome;
	private Date fim;

	@PostConstruct
	private void init() {
		this.cadastrarEstudoUsuario = new CadastrarEstudoUsuarioImpl(
				this.estudoUsuarioGateway);
	}

	public String cadastrar() {
		this.cadastrarEstudoUsuario.execute(this.usuarioService.getEmail(),
				this.nome, this.fim);
		this.context.addMessage(null, new FacesMessage("Sucesso",
				"Estudo cadastrado"));
		return "main";
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

}
