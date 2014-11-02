package br.com.guarasoft.studyware.usuarioestudo.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
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
import br.com.guarasoft.studyware.menu.controller.MenuController;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.casodeuso.CadastrarUsuarioEstudo;
import br.com.guarasoft.studyware.usuarioestudo.casodeuso.CadastrarUsuarioEstudoImpl;
import br.com.guarasoft.studyware.usuarioestudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.usuarioestudo.gateway.UsuarioEstudoGateway;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.UsuarioEstudoMateriaGateway;

@ManagedBean(name = "usuarioEstudo")
@ViewScoped
@Data
public class UsuarioEstudoController implements Serializable {

	private static final long serialVersionUID = -2586448860897763084L;

	@Inject
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private UsuarioEstudoGateway usuarioEstudoGateway;

	@Inject
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private UsuarioEstudoMateriaGateway usuarioEstudoMateriaGateway;

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

	private DualListModel<UsuarioEstudoMateriaBean> materias;

	private List<UsuarioEstudoBean> estudos;

	private UsuarioEstudoBean bean;

	@PostConstruct
	private void init() {
		this.cadastrarUsuarioEstudo = new CadastrarUsuarioEstudoImpl(this.usuarioEstudoGateway);

		List<MateriaBean> materiasRestantes;
		List<UsuarioEstudoMateriaBean> materiasUsuario;

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		this.bean = (UsuarioEstudoBean) ec.getFlash().get("usuarioEstudo");
		if (this.bean == null) {
			this.bean = new UsuarioEstudoBean();
			this.bean.setEmail(this.usuarioService.getEmail());

			materiasRestantes = this.materiaGateway.buscaMaterias();

			materiasUsuario = new ArrayList<>();
		} else {
			materiasRestantes = this.materiaGateway.buscaMateriasRestantes(this.bean);

			materiasUsuario = this.bean.getMaterias();
		}

		List<UsuarioEstudoMateriaBean> usuarioEstudoMateriasRestantes = new ArrayList<>();

		for (MateriaBean materiaBean : materiasRestantes) {
			UsuarioEstudoMateriaBean usuarioEstudoMateriaBean = new UsuarioEstudoMateriaBean();
			usuarioEstudoMateriaBean.setUsuarioEstudoBean(this.bean);
			usuarioEstudoMateriaBean.setMateriaBean(materiaBean);
			usuarioEstudoMateriasRestantes.add(usuarioEstudoMateriaBean);
		}

		this.materias = new DualListModel<>(usuarioEstudoMateriasRestantes, materiasUsuario);

		this.estudos = this.usuarioEstudoGateway.recuperaEstudos(this.usuarioService.getEmail());
	}

	public String onFlowProcess(FlowEvent event) {
		// String newStep = event.getNewStep();
		// if ("horas".equals(newStep)) {
		// }
		// return newStep;
		return event.getNewStep();
	}

	public void cadastrar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastrarUsuarioEstudo.execute(this.bean);
			context.addMessage(null, new FacesMessage("Sucesso", "Estudo cadastrado"));
		} catch (UsuarioEstudoJaExiste e) {
			context.addMessage(null, new FacesMessage("Falha", "Estudo já exite"));
		}
	}

	public String alterar(UsuarioEstudoBean usuarioEstudoBean) {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.getFlash().put("usuarioEstudo", usuarioEstudoBean);

		return new MenuController().cadastrarUsuarioEstudo();
	}

}
