package br.com.guarasoft.studyware.banca.casodeuso;

import java.util.Collection;

import br.com.guarasoft.studyware.banca.gateway.BancaGateway;
import br.com.guarasoft.studyware.banca.modelo.Banca;

public class ConsultaBancaImpl implements ConsultaBanca {

	private BancaGateway bancaGateway;

	public ConsultaBancaImpl(BancaGateway bancaGateway) {
		this.bancaGateway = bancaGateway;
	}

	@Override
	public Collection<Banca> consulta() {
		Collection<Banca> bancas = bancaGateway.consulta();
		return bancas;
	}

}
