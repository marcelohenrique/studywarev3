package br.com.guarasoft.studyware.usuario.casosdeuso;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuario.casodeuso.CadastrarUsuario;
import br.com.guarasoft.studyware.usuario.casodeuso.CadastrarUsuarioImpl;
import br.com.guarasoft.studyware.usuario.excecao.EmailJaCadastrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarUsuarioTest {

	@Mock
	private UsuarioGateway usuarioGateway;

	@Test
	public void cadastrarSucesso() {
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@gmail.com");

		CadastrarUsuario cadastrarUsuario = new CadastrarUsuarioImpl(this.usuarioGateway);
		Boolean sucesso = cadastrarUsuario.executar(usuario);

		assertTrue(sucesso);
	}

	@Test(expected = EmailJaCadastrado.class)
	public void cadastrarEmailJaCadastrado() {
		Usuario usuario = new Usuario();
		usuario.setEmail("teste@gmail.com");

		when(this.usuarioGateway.pesquisaPorEmail(anyString())).thenReturn(new Usuario());

		CadastrarUsuario cadastrarUsuario = new CadastrarUsuarioImpl(this.usuarioGateway);
		cadastrarUsuario.executar(usuario);
	}

}
