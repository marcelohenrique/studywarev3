package br.com.guarasoft.studyware.estudo.casodeuso;

import br.com.guarasoft.studyware.estudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.estudo.gateway.UsuarioEstudoGateway;

public class RemoverUsuarioEstudoImpl implements RemoverUsuarioEstudo {

	private final UsuarioEstudoGateway usuarioEstudoGateway;

	public RemoverUsuarioEstudoImpl(UsuarioEstudoGateway usuarioEstudoGateway) {
		this.usuarioEstudoGateway = usuarioEstudoGateway;
	}

	@Override
	public void execute(UsuarioEstudoBean usuarioEstudo) {
		this.usuarioEstudoGateway.remover(usuarioEstudo);
	}

}
