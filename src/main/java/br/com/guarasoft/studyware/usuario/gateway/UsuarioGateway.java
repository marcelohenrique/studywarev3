package br.com.guarasoft.studyware.usuario.gateway;

import java.util.Collection;

import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public interface UsuarioGateway {

	void cadastrar(Usuario usuario);

	Usuario pesquisaPorEmail(String email);

	Collection<Usuario> buscaUsuarios();

	void alterar(Usuario usuario);

	Collection<Usuario> buscaUsuarios(String email);

}
