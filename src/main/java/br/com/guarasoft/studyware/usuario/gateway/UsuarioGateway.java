package br.com.guarasoft.studyware.usuario.gateway;

import java.util.List;

import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public interface UsuarioGateway {

	void cadastrar(Usuario usuario);

	Usuario pesquisaPorEmail(String email);

	List<Usuario> buscaUsuarios();

	void alterar(Usuario usuario);

}
