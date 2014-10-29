package br.com.guarasoft.studyware.usuarioestudo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;
import br.com.guarasoft.studyware.usuarioestudo.casodeuso.CadastrarUsuarioEstudo;
import br.com.guarasoft.studyware.usuarioestudo.casodeuso.CadastrarUsuarioEstudoImpl;
import br.com.guarasoft.studyware.usuarioestudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.usuarioestudo.gateway.UsuarioEstudoGateway;

@ManagedBean(name = "estudoUsuario")
@ViewScoped
@Data
public class UsuarioEstudoController {

	@Inject
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private UsuarioEstudoGateway usuarioEstudoGateway;
	@Inject
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private MateriaGateway materiaGateway;
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private CadastrarUsuarioEstudo cadastrarUsuarioEstudo;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	@Getter(AccessLevel.PRIVATE)
	private UsuarioService usuarioService;

	private String nome;
	private Date fim;
	private DualListModel<MateriaBean> materias;

	@PostConstruct
	private void init() {
		this.cadastrarUsuarioEstudo = new CadastrarUsuarioEstudoImpl(
				this.usuarioEstudoGateway);

		List<MateriaBean> materias = this.materiaGateway.buscaMaterias();
		this.materias = new DualListModel<>(materias,
				new ArrayList<MateriaBean>());
	}

	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
	}

	public void cadastrar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastrarUsuarioEstudo.execute(this.usuarioService.getEmail(),
					this.nome, this.fim);
			context.addMessage(null, new FacesMessage("Sucesso",
					"Estudo cadastrado"));
		} catch (UsuarioEstudoJaExiste e) {
			context.addMessage(null, new FacesMessage("Falha",
					"Estudo já exite"));
		}
	}

}
