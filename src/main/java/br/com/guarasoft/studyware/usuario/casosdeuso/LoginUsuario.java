package br.com.guarasoft.studyware.usuario.casosdeuso;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;

public interface LoginUsuario {

	UsuarioBean autentica(String email);

}
