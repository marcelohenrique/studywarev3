package br.com.guarasoft.studyware.materia.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.com.guarasoft.studyware.materia.casodeuso.CadastrarMateria;
import br.com.guarasoft.studyware.materia.casodeuso.CadastrarMateriaImpl;
import br.com.guarasoft.studyware.materia.casodeuso.RemoverMateria;
import br.com.guarasoft.studyware.materia.casodeuso.RemoverMateriaImpl;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.materia.modelo.Materia;
import br.com.guarasoft.studyware.menu.controller.MenuController;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Named("materia")
@Data
public class MateriaController {

	private String sigla;
	private String nome;

	@Setter(AccessLevel.PRIVATE)
	private List<Materia> materias;

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

	public void remover(Materia materia) {
		RemoverMateria removerMateria = new RemoverMateriaImpl(this.materiaGateway);
		removerMateria.execute(materia);
		this.materias = this.materiaGateway.buscaMaterias();

		FacesMessage msg = new FacesMessage("Matéria Removida", materia.getSigla());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowEdit(RowEditEvent event) {
		Materia materia = (Materia) event.getObject();

		this.materiaGateway.alterar(materia);

		FacesMessage msg = new FacesMessage("Matéria Editada", materia.getSigla());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		Materia materia = (Materia) event.getObject();

		FacesMessage msg = new FacesMessage("Alteração Cancelada", materia.getSigla());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
