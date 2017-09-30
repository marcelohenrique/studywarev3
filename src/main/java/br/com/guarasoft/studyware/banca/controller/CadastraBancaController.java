package br.com.guarasoft.studyware.banca.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.omnifaces.cdi.ViewScoped;

import br.com.guarasoft.studyware.banca.casodeuso.CadastraBanca;
import br.com.guarasoft.studyware.banca.casodeuso.CadastraBancaImpl;
import br.com.guarasoft.studyware.banca.gateway.BancaGateway;
import br.com.guarasoft.studyware.banca.modelo.Banca;
import br.com.guarasoft.studyware.infra.faces.FacesUtil;
import br.com.guarasoft.studyware.menu.controller.MenuController;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class CadastraBancaController implements Serializable {

	private static final long serialVersionUID = -2586448860897763084L;

	private @Inject FacesUtil facesUtil;
	private @Inject BancaGateway bancaGateway;

	private CadastraBanca cadastraBanca;

	@Getter
	@Setter
	@NotNull
	private String nome;
	@Getter
	@Setter
	@NotNull
	private String descricao;
	@Getter
	@Setter
	@NotNull
	private String site;

	@PostConstruct
	private void init() {
		this.cadastraBanca = new CadastraBancaImpl(this.bancaGateway);
	}

	public String cadastrar() {
		Banca banca = new Banca();
		banca.setNome(this.nome);
		banca.setDescricao(this.descricao);
		banca.setSite(this.site);
		this.cadastraBanca.cadastrar(banca);
		this.facesUtil.addInfo("Sucesso", "Banca cadastrada com sucesso!");
		return new MenuController().consultarBanca();
	}

}
