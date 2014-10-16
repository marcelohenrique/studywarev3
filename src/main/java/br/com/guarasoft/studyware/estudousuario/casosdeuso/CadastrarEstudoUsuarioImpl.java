package br.com.guarasoft.studyware.estudousuario.casosdeuso;

import br.com.guarasoft.studyware.estudousuario.gateway.EstudoUsuarioGateway;

public class CadastrarEstudoUsuarioImpl implements CadastrarEstudoUsuario {

	private EstudoUsuarioGateway estudoUsuarioGateway;

	public CadastrarEstudoUsuarioImpl(EstudoUsuarioGateway estudoUsuarioGateway) {
		this.estudoUsuarioGateway = estudoUsuarioGateway;
	}

	@Override
	public Boolean execute(String email, String nomeEstudo) {
		return this.estudoUsuarioGateway.cadastrar(email, nomeEstudo);
	}

}
