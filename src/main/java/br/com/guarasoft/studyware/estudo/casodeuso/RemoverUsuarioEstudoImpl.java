package br.com.guarasoft.studyware.estudo.casodeuso;

import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;

public class RemoverUsuarioEstudoImpl implements RemoverUsuarioEstudo {

	private final EstudoGateway usuarioEstudoGateway;

	public RemoverUsuarioEstudoImpl(EstudoGateway usuarioEstudoGateway) {
		this.usuarioEstudoGateway = usuarioEstudoGateway;
	}

	@Override
	public void execute(Estudo estudo) {
		this.usuarioEstudoGateway.remover(estudo);
	}

}
