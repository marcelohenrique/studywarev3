package br.com.guarasoft.studyware.materia.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import org.primefaces.event.RowEditEvent;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.casodeuso.CadastrarMateria;
import br.com.guarasoft.studyware.materia.casodeuso.CadastrarMateriaImpl;
import br.com.guarasoft.studyware.materia.casodeuso.RemoverMateria;
import br.com.guarasoft.studyware.materia.casodeuso.RemoverMateriaImpl;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.menu.controller.MenuController;

@ManagedBean(name = "materia")
@Data
public class MateriaController {

	private String sigla;
	private String nome;

	@Setter(AccessLevel.PRIVATE)
	private List<MateriaBean> materias;

	@Inject
	private MateriaGateway materiaGateway;

	@PostConstruct
	private void init() {
		this.materias = this.materiaGateway.buscaMaterias();
	}

	public String cadastrar() {
		CadastrarMateria cadastrarMateria = new CadastrarMateriaImpl(this.materiaGateway);

		cadastrarMateria.execute(this.sigla, this.nome);
		this.materias = this.materiaGateway.buscaMaterias();

		FacesMessage msg = new FacesMessage("Matéria Cadastrada", this.sigla);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		return new MenuController().consultarMateria();
	}

	public void remover(MateriaBean materiaBean) {
		RemoverMateria removerMateria = new RemoverMateriaImpl(this.materiaGateway);
		removerMateria.execute(materiaBean);
		this.materias = this.materiaGateway.buscaMaterias();

		FacesMessage msg = new FacesMessage("Matéria Removida", materiaBean.getSigla());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowEdit(RowEditEvent event) {
		MateriaBean materiaBean = (MateriaBean) event.getObject();

		this.materiaGateway.alterar(materiaBean);

		FacesMessage msg = new FacesMessage("Matéria Editada", materiaBean.getSigla());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		MateriaBean materiaBean = (MateriaBean) event.getObject();

		FacesMessage msg = new FacesMessage("Alteração Cancelada", materiaBean.getSigla());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
