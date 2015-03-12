package br.com.guarasoft.studyware.usuario.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.guarasoft.studyware.menu.controller.MenuController;
import br.com.guarasoft.studyware.usuario.casodeuso.CadastrarUsuario;
import br.com.guarasoft.studyware.usuario.casodeuso.CadastrarUsuarioImpl;
import br.com.guarasoft.studyware.usuario.excecao.EmailJaCadastrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@ManagedBean(name = "usuario")
public class UsuarioController {

	private final FacesContext context = FacesContext.getCurrentInstance();

	@Inject
	private UsuarioGateway usuarioGateway;
	private CadastrarUsuario cadastrarUsuario;

	private String email;
	private boolean ativo;

	@PostConstruct
	private void init() {
		this.cadastrarUsuario = new CadastrarUsuarioImpl(this.usuarioGateway);
	}

	public String cadastrar() {
		try {
			Usuario usuario = new Usuario();
			usuario.setEmail(this.email);
			this.cadastrarUsuario.executar(usuario);
			this.context.addMessage(null, new FacesMessage("Sucesso", "E-mail cadastrado"));
			return new MenuController().consultarUsuario();
		} catch (EmailJaCadastrado e) {
			this.context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "E-mail já cadastrado"));
			return null;
		}
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
