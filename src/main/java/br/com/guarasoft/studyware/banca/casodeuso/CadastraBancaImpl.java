package br.com.guarasoft.studyware.banca.casodeuso;

import br.com.guarasoft.studyware.banca.gateway.BancaGateway;
import br.com.guarasoft.studyware.banca.modelo.Banca;

public class CadastraBancaImpl implements CadastraBanca {

	private BancaGateway bancaGateway;

	public CadastraBancaImpl(BancaGateway bancaGateway) {
		this.bancaGateway = bancaGateway;
	}

	@Override
	public void cadastrar(Banca banca) {
		this.bancaGateway.cadastra(banca);
	}

}
