package br.com.guarasoft.studyware.usuario.casosdeuso;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuario.casodeuso.LoginUsuario;
import br.com.guarasoft.studyware.usuario.casodeuso.LoginUsuarioImpl;
import br.com.guarasoft.studyware.usuario.excecao.EmailNaoEncontrado;
import br.com.guarasoft.studyware.usuario.excecao.UsuarioInativo;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class LoginUsuarioTest {

	@Mock
	private UsuarioGateway usuarioGateway;

	@Test
	public void autenticarSucesso() {
		String email = "teste@gmail.com";

		Usuario usuarioMock = new Usuario();
		usuarioMock.setEmail(email);
		usuarioMock.setAtivo(true);
		when(this.usuarioGateway.pesquisaPorEmail(anyString())).thenReturn(usuarioMock);

		LoginUsuario loginUsuario = new LoginUsuarioImpl(this.usuarioGateway);
		Usuario usuario = loginUsuario.autentica(email);

		assertNotNull(usuario);
	}

	@Test(expected = EmailNaoEncontrado.class)
	public void autenticarFalha() {
		String email = "teste@gmail.com";

		LoginUsuario loginUsuario = new LoginUsuarioImpl(this.usuarioGateway);
		loginUsuario.autentica(email);
	}

	@Test(expected = UsuarioInativo.class)
	public void autenticaUsuarioInativo() {
		String email = "teste@gmail.com";

		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setAtivo(false);
		when(this.usuarioGateway.pesquisaPorEmail(anyString())).thenReturn(usuario);

		LoginUsuario loginUsuario = new LoginUsuarioImpl(this.usuarioGateway);
		loginUsuario.autentica(email);
	}

}
