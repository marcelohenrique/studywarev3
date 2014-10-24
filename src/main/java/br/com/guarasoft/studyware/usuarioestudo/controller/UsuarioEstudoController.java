package br.com.guarasoft.studyware.usuarioestudo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DualListModel;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;
import br.com.guarasoft.studyware.usuarioestudo.casodeuso.CadastrarUsuarioEstudo;
import br.com.guarasoft.studyware.usuarioestudo.casodeuso.CadastrarUsuarioEstudoImpl;
import br.com.guarasoft.studyware.usuarioestudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.usuarioestudo.gateway.UsuarioEstudoGateway;

@ManagedBean(name = "estudoUsuario")
public class UsuarioEstudoController {

	@Inject
	private UsuarioEstudoGateway usuarioEstudoGateway;
	@Inject
	private MateriaGateway materiaGateway;
	private CadastrarUsuarioEstudo cadastrarUsuarioEstudo;

	@ManagedProperty(value = "#{sessionAuth.usuario}")
	private UsuarioService usuarioService;

	private DualListModel<MateriaBean> materias;

	private String nome;
	private Date fim;
	private List<MateriaBean> materiasSelecionadas = new ArrayList<>();

	@PostConstruct
	private void init() {
		this.cadastrarUsuarioEstudo = new CadastrarUsuarioEstudoImpl(
				this.usuarioEstudoGateway);

		List<MateriaBean> materias = this.materiaGateway.buscaMaterias();
		this.materias = new DualListModel<>(materias, this.materiasSelecionadas);
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

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public DualListModel<MateriaBean> getMaterias() {
		return materias;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

}
