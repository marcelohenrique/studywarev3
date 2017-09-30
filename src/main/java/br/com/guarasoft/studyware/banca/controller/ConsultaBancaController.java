package br.com.guarasoft.studyware.banca.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import br.com.guarasoft.studyware.banca.casodeuso.ConsultaBanca;
import br.com.guarasoft.studyware.banca.casodeuso.ConsultaBancaImpl;
import br.com.guarasoft.studyware.banca.gateway.BancaGateway;
import br.com.guarasoft.studyware.banca.modelo.Banca;
import lombok.Getter;

@Named
@ViewScoped
public class ConsultaBancaController implements Serializable {

	private static final long serialVersionUID = -2586448860897763084L;

	private @Inject BancaGateway bancaGateway;

	private ConsultaBanca consultaBanca;

	@Getter
	private Collection<Banca> bancas;

	@PostConstruct
	private void init() {
		this.consultaBanca = new ConsultaBancaImpl(this.bancaGateway);

		this.bancas = this.consultaBanca.consulta();
	}

}
