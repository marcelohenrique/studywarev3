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

	private final FacesContext context = FacesContext.getCurrentInstance();

	@Inject
	private UsuarioGateway usuarioGateway;
	private CadastrarUsuario cadastrarUsuario;

	private String email;

	@PostConstruct
	private void init() {
		this.cadastrarUsuario = new CadastrarUsuarioImpl(this.usuarioGateway);
	}

	public String cadastrar() {
		try {
			this.cadastrarUsuario.executar(this.email);
			this.context.addMessage(null, new FacesMessage("Sucesso", "E-mail cadastrado"));
			return "main";
		} catch (EmailJaCadastrado e) {
			this.context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "E-mail já cadastrado"));
			return "pages/usuario/cadastro";
		}
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
