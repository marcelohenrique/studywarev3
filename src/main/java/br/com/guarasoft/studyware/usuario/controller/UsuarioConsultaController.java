package br.com.guarasoft.studyware.usuario.controller;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import br.com.guarasoft.studyware.usuario.casodeuso.AlterarUsuario;
import br.com.guarasoft.studyware.usuario.casodeuso.AlterarUsuarioImpl;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@ManagedBean(name = "usuarioConsulta")
public class UsuarioConsultaController {

	@Inject
	private UsuarioGateway usuarioGateway;
	private AlterarUsuario alterarUsuario;

	private Collection<Usuario> usuarios;

	@PostConstruct
	private void init() {
		this.alterarUsuario = new AlterarUsuarioImpl(this.usuarioGateway);

		this.usuarios = this.usuarioGateway.buscaUsuarios();
	}

	public void onRowEdit(RowEditEvent event) {
		Usuario usuario = (Usuario) event.getObject();

		this.alterarUsuario.execute(usuario);

		FacesMessage msg = new FacesMessage("Alteração Realizada", usuario.getEmail());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		Usuario usuario = (Usuario) event.getObject();

		FacesMessage msg = new FacesMessage("Alteração Cancelada", usuario.getEmail());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Collection<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
