package br.com.guarasoft.studyware.usuario.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.guarasoft.studyware.usuario.casosdeuso.CadastrarUsuario;
import br.com.guarasoft.studyware.usuario.casosdeuso.CadastrarUsuarioImpl;
import br.com.guarasoft.studyware.usuario.excecoes.EmailJaCadastrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

@ManagedBean(name = "usuario")
public class UsuarioController {

	private String email;

	private CadastrarUsuario cadastrarUsuario;

	@Inject
	private UsuarioGateway usuarioGateway;

	@PostConstruct
	private void init() {
		this.cadastrarUsuario = new CadastrarUsuarioImpl(this.usuarioGateway);
	}

	public String cadastrar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastrarUsuario.executar(this.email);
			context.addMessage(null, new FacesMessage("Sucesso", "E-mail cadastrado"));
			return "main";
		} catch (EmailJaCadastrado e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "E-mail já cadastrado"));
			return "pages/usuario/cadastro";
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
