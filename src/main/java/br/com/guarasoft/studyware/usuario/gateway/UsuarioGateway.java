package br.com.guarasoft.studyware.usuario.gateway;

import br.com.guarasoft.studyware.usuario.entidades.Usuario;

public interface UsuarioGateway {

	Usuario pesquisaPorEmail(String email);

}
