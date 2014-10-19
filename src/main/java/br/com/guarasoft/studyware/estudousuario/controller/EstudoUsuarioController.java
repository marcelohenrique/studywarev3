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
import br.com.guarasoft.studyware.estudousuario.excecoes.EstudoJaExiste;
import br.com.guarasoft.studyware.estudousuario.gateway.EstudoUsuarioGateway;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;

@ManagedBean(name = "estudoUsuario")
public class EstudoUsuarioController {

	@Inject
	private EstudoUsuarioGateway estudoUsuarioGateway;
	private CadastrarEstudoUsuario cadastrarEstudoUsuario;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	private UsuarioService usuarioService;

	private String nome;
	private Date fim;

	@PostConstruct
	private void init() {
		this.cadastrarEstudoUsuario = new CadastrarEstudoUsuarioImpl(this.estudoUsuarioGateway);
	}

	public void cadastrar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastrarEstudoUsuario.execute(this.usuarioService.getEmail(), this.nome, this.fim);
			context.addMessage(null, new FacesMessage("Sucesso", "Estudo cadastrado"));
		} catch (EstudoJaExiste e) {
			context.addMessage(null, new FacesMessage("Falha", "Estudo já exite"));
		}
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
