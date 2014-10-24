package br.com.guarasoft.studyware.usuarioestudo.casodeuso;

import java.util.Date;

import br.com.guarasoft.studyware.usuarioestudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.usuarioestudo.gateway.UsuarioEstudoGateway;

public class CadastrarUsuarioEstudoImpl implements CadastrarUsuarioEstudo {

	private UsuarioEstudoGateway usuarioEstudoGateway;

	public CadastrarUsuarioEstudoImpl(UsuarioEstudoGateway usuarioEstudoGateway) {
		this.usuarioEstudoGateway = usuarioEstudoGateway;
	}

	@Override
	public void execute(String email, String nomeEstudo, Date fim) {
		try {
			this.usuarioEstudoGateway.cadastrar(email, nomeEstudo, fim);
		} catch (Exception e) {
			throw new UsuarioEstudoJaExiste();
		}
	}
}
