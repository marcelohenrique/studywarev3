package br.com.guarasoft.studyware.usuarioestudo.gateway;

import java.util.List;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public interface UsuarioEstudoGateway {

	void cadastrar(UsuarioEstudoBean usuarioEstudoBean);

	List<UsuarioEstudoBean> recuperaTodosEstudos(String email);

	List<UsuarioEstudoBean> recuperaEstudosValidos(String email);

	UsuarioEstudoBean buscaPorId(Long id);

	void remover(UsuarioEstudoBean usuarioEstudo);

}
