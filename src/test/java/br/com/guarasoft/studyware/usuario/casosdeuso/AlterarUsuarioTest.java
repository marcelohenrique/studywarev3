package br.com.guarasoft.studyware.usuario.casosdeuso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuario.casodeuso.AlterarUsuario;
import br.com.guarasoft.studyware.usuario.casodeuso.AlterarUsuarioImpl;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class AlterarUsuarioTest {

	@Mock
	private UsuarioGateway usuarioGateway;

	@Test
	public void alterarUsuarioSucesso() {
		Usuario usuario = new Usuario();

		AlterarUsuario alterarUsuario = new AlterarUsuarioImpl(this.usuarioGateway);
		alterarUsuario.execute(usuario);
	}

}
