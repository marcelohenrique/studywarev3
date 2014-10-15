package br.com.guarasoft.studyware.usuario.gateway;

import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;

public interface UsuarioGateway {

	UsuarioService pesquisaPorEmail(String email);

	void cadastrar(String email);

}
