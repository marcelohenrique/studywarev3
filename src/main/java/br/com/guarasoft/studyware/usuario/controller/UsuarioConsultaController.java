package br.com.guarasoft.studyware.usuario.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;
import br.com.guarasoft.studyware.usuario.casosdeuso.AlterarUsuario;
import br.com.guarasoft.studyware.usuario.casosdeuso.AlterarUsuarioImpl;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

@ManagedBean(name = "usuarioConsulta")
public class UsuarioConsultaController {

	@Inject
	private UsuarioGateway usuarioGateway;
	private AlterarUsuario alterarUsuario;

	private List<UsuarioBean> usuarios;

	@PostConstruct
	private void init() {
		this.alterarUsuario = new AlterarUsuarioImpl(this.usuarioGateway);

		this.usuarios = this.usuarioGateway.buscaUsuarios();
	}

	public void onRowEdit(RowEditEvent event) {
		UsuarioBean usuario = (UsuarioBean) event.getObject();

		this.alterarUsuario.execute(usuario);

		FacesMessage msg = new FacesMessage("Alteração Realizada", usuario.getEmail());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		UsuarioBean usuario = (UsuarioBean) event.getObject();

		FacesMessage msg = new FacesMessage("Alteração Cancelada", usuario.getEmail());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<UsuarioBean> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<UsuarioBean> usuarios) {
		this.usuarios = usuarios;
	}

}
