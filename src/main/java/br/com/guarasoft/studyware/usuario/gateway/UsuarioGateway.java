package br.com.guarasoft.studyware.usuario.gateway;

import java.util.List;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;

public interface UsuarioGateway {

	void cadastrar(UsuarioBean usuario);

	UsuarioBean pesquisaPorEmail(String email);

	List<UsuarioBean> buscaUsuarios();

	void alterar(UsuarioBean usuario);

}
