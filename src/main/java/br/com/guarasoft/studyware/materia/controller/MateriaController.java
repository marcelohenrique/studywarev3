package br.com.guarasoft.studyware.materia.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.RowEditEvent;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.casodeuso.CadastrarMateria;
import br.com.guarasoft.studyware.materia.casodeuso.CadastrarMateriaImpl;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;

@ManagedBean(name = "materia")
public class MateriaController {

	private String sigla;
	private String nome;

	private List<MateriaBean> materias;

	@Inject
	private MateriaGateway materiaGateway;

	@PostConstruct
	private void init() {
		this.materias = this.materiaGateway.buscaMaterias();
	}

	public void cadastrar() {
		CadastrarMateria cadastrarMateria = new CadastrarMateriaImpl(
				this.materiaGateway);
		cadastrarMateria.execute(this.sigla, this.nome);
	}

	public void onRowEdit(RowEditEvent event) {
		MateriaBean materiaBean = (MateriaBean) event.getObject();

		this.materiaGateway.alterar(materiaBean);

		FacesMessage msg = new FacesMessage("Matéria Editada",
				materiaBean.getSigla());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		MateriaBean materiaBean = (MateriaBean) event.getObject();

		FacesMessage msg = new FacesMessage("Alteração Cancelada",
				materiaBean.getSigla());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<MateriaBean> getMaterias() {
		return materias;
	}

}
