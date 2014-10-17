package br.com.guarasoft.studyware.estudousuario.gateway;

import java.util.Date;

public interface EstudoUsuarioGateway {

	void cadastrar(String email, String nomeEstudo, Date fim);

}
