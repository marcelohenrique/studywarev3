package br.com.guarasoft.studyware.usuario.casosdeuso;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuario.casosdeuso.LoginUsuario;
import br.com.guarasoft.studyware.usuario.casosdeuso.LoginUsuarioImpl;
import br.com.guarasoft.studyware.usuario.entidades.Usuario;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioImpl;
import br.com.guarasoft.studyware.usuario.excecoes.EmailNaoEncontrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

@RunWith(MockitoJUnitRunner.class)
public class LoginUsuarioTest {

	@Mock
	private UsuarioGateway usuarioGateway;

	@Test
	public void autenticarSucesso() {
		when(usuarioGateway.pesquisaPorEmail(anyString())).thenReturn(new UsuarioImpl());

		String email = "teste@gmail.com";

		LoginUsuario loginUsuario = new LoginUsuarioImpl(usuarioGateway);
		Usuario usuario = loginUsuario.autenticar(email);

		assertNotNull(usuario);
	}

	@Test(expected = EmailNaoEncontrado.class)
	public void autenticarFalha() {
		String email = "teste@gmail.com";

		LoginUsuario loginUsuario = new LoginUsuarioImpl(usuarioGateway);
		loginUsuario.autenticar(email);
	}

}
