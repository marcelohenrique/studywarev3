package br.com.guarasoft.studyware.usuario.casosdeuso;

import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;

public interface LoginUsuario {

	UsuarioService autenticar(String email);

}
