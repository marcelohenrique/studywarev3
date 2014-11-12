package br.com.guarasoft.studyware.usuario.casosdeuso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

@RunWith(MockitoJUnitRunner.class)
public class AlterarUsuarioTest {

	@Mock
	private UsuarioGateway usuarioGateway;

	@Test
	public void alterarUsuarioSucesso() {
		UsuarioBean usuario = new UsuarioBean();

		AlterarUsuario alterarUsuario = new AlterarUsuarioImpl(this.usuarioGateway);
		alterarUsuario.execute(usuario);
	}

}
