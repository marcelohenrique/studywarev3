package br.com.guarasoft.studyware.estudo.casodeuso;

import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;

public class RemoveEstudoImpl implements RemoveEstudo {

	private final EstudoGateway estudoGateway;

	public RemoveEstudoImpl(EstudoGateway estudoGateway) {
		this.estudoGateway = estudoGateway;
	}

	@Override
	public void execute(Estudo estudo) {
		this.estudoGateway.remover(estudo);
	}

}
