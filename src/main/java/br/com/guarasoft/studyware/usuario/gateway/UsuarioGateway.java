package br.com.guarasoft.studyware.usuario.gateway;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;

public interface UsuarioGateway {

	UsuarioBean pesquisaPorEmail(String email);

	void cadastrar(UsuarioBean usuario);

}
