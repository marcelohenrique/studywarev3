package br.com.guarasoft.studyware.estudo.casodeuso;

import br.com.guarasoft.studyware.estudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;

public class CadastrarEstudo {

	private final EstudoGateway usuarioEstudoGateway;

	public CadastrarEstudo(EstudoGateway usuarioEstudoGateway) {
		this.usuarioEstudoGateway = usuarioEstudoGateway;
	}

	public void execute(Estudo estudo) {
		try {
			this.usuarioEstudoGateway.cadastrar(estudo);
		} catch (Exception e) {
			throw new UsuarioEstudoJaExiste();
		}
	}

	public CadastrarEstudo setNomeEstudo(String nomeEstudo) {
		return null;
	}

	public void execute() {
	}

}
