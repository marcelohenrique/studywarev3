package br.com.guarasoft.studyware.usuario.casosdeuso;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;
import br.com.guarasoft.studyware.usuario.excecoes.EmailJaCadastrado;
import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarUsuarioTest {

	@Mock
	private UsuarioGateway usuarioGateway;

	@Test
	public void cadastrarSucesso() {
		UsuarioBean usuario = new UsuarioBean();
		usuario.setEmail("teste@gmail.com");

		CadastrarUsuario cadastrarUsuario = new CadastrarUsuarioImpl(this.usuarioGateway);
		Boolean sucesso = cadastrarUsuario.executar(usuario);

		assertTrue(sucesso);
	}

	@Test(expected = EmailJaCadastrado.class)
	public void cadastrarEmailJaCadastrado() {
		UsuarioBean usuario = new UsuarioBean();
		usuario.setEmail("teste@gmail.com");

		when(this.usuarioGateway.pesquisaPorEmail(anyString())).thenReturn(new UsuarioBean());

		CadastrarUsuario cadastrarUsuario = new CadastrarUsuarioImpl(this.usuarioGateway);
		cadastrarUsuario.executar(usuario);
	}

}
