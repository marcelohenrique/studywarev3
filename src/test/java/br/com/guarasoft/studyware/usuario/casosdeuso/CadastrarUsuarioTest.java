package br.com.guarasoft.studyware.usuario.casosdeuso;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuario.entidades.UsuarioServiceImpl;
import br.com.guarasoft.studyware.usuario.excecoes.EmailJaCadastrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarUsuarioTest {

	@Mock
	private UsuarioGateway usuarioGateway;

	@Test
	public void cadastrarSucesso() {
		String email = "teste@gmail.com";

		CadastrarUsuario cadastrarUsuario = new CadastrarUsuarioImpl(this.usuarioGateway);
		Boolean sucesso = cadastrarUsuario.executar(email);

		assertTrue(sucesso);
	}

	@Test(expected = EmailJaCadastrado.class)
	public void cadastrarEmailJaCadastrado() {
		String email = "teste@gmail.com";

		when(usuarioGateway.pesquisaPorEmail(anyString())).thenReturn(new UsuarioServiceImpl(email));

		CadastrarUsuario cadastrarUsuario = new CadastrarUsuarioImpl(this.usuarioGateway);
		cadastrarUsuario.executar(email);
	}

}
