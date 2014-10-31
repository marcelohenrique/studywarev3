package br.com.guarasoft.studyware.usuario.casosdeuso;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuario.casosdeuso.LoginUsuario;
import br.com.guarasoft.studyware.usuario.casosdeuso.LoginUsuarioImpl;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioService;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioServiceImpl;
import br.com.guarasoft.studyware.usuario.excecoes.EmailNaoEncontrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

@RunWith(MockitoJUnitRunner.class)
public class LoginUsuarioTest {

	@Mock
	private UsuarioGateway usuarioGateway;

	@Test
	public void autenticarSucesso() {
		String email = "teste@gmail.com";
		when(usuarioGateway.pesquisaPorEmail(anyString())).thenReturn(new UsuarioServiceImpl(email));

		LoginUsuario loginUsuario = new LoginUsuarioImpl(usuarioGateway);
		UsuarioService usuarioService = loginUsuario.autenticar(email);

		assertNotNull(usuarioService);
	}

	@Test(expected = EmailNaoEncontrado.class)
	public void autenticarFalha() {
		String email = "teste@gmail.com";

		LoginUsuario loginUsuario = new LoginUsuarioImpl(usuarioGateway);
		loginUsuario.autenticar(email);
	}

}
