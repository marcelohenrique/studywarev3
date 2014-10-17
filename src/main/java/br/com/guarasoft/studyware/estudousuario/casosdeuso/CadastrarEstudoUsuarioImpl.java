package br.com.guarasoft.studyware.estudousuario.casosdeuso;

import java.util.Date;

import br.com.guarasoft.studyware.estudousuario.gateway.EstudoUsuarioGateway;

public class CadastrarEstudoUsuarioImpl implements CadastrarEstudoUsuario {

	private EstudoUsuarioGateway estudoUsuarioGateway;

	public CadastrarEstudoUsuarioImpl(EstudoUsuarioGateway estudoUsuarioGateway) {
		this.estudoUsuarioGateway = estudoUsuarioGateway;
	}

	@Override
	public void execute(String email, String nomeEstudo, Date fim) {
		this.estudoUsuarioGateway.cadastrar(email, nomeEstudo, fim);
	}
}
