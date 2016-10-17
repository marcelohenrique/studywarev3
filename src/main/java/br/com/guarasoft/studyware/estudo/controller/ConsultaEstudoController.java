package br.com.guarasoft.studyware.estudo.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.com.guarasoft.studyware.acesso.controller.SessionAuth;
import br.com.guarasoft.studyware.estudo.casodeuso.ConsultaEstudo;
import br.com.guarasoft.studyware.estudo.casodeuso.ConsultaEstudoImpl;
import br.com.guarasoft.studyware.estudo.casodeuso.RemoveEstudo;
import br.com.guarasoft.studyware.estudo.casodeuso.RemoveEstudoImpl;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.menu.controller.MenuController;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;
import lombok.Getter;

@Named("consultaEstudoController")
@ViewScoped
public class ConsultaEstudoController implements Serializable {

	private static final long serialVersionUID = -2586448860897763084L;

	private ConsultaEstudo consultaEstudo;
	private RemoveEstudo removeEstudo;

	private @Inject SessionAuth sessionAuth;
	private @Inject EstudoGateway estudoGateway;

	private Usuario usuario;

	@Getter
	private Collection<Estudo> estudos;

	@PostConstruct
	private void init() {
		this.usuario = this.sessionAuth.getUsuario();

		this.consultaEstudo = new ConsultaEstudoImpl(this.estudoGateway);
		this.removeEstudo = new RemoveEstudoImpl(this.estudoGateway);

		this.estudos = this.consultaEstudo.consulta(this.usuario);
	}

	public String alterar(Estudo estudo) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.getFlash().put("estudo", estudo);

		return new MenuController().cadastrarEstudo();
	}

	public void remover(Estudo estudo) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.removeEstudo.execute(estudo);

			this.estudos = this.consultaEstudo.consulta(this.usuario);

			context.addMessage(null, new FacesMessage("Sucesso", "Estudo removido"));
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Erro", "Erro ao remover o estudo"));
		}
	}

}
