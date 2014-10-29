package br.com.guarasoft.studyware.usuarioestudo.casodeuso;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuarioestudo.casodeuso.CadastrarUsuarioEstudo;
import br.com.guarasoft.studyware.usuarioestudo.casodeuso.CadastrarUsuarioEstudoImpl;
import br.com.guarasoft.studyware.usuarioestudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.usuarioestudo.gateway.UsuarioEstudoGateway;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarUsuarioEstudoTest {

	@Mock
	private UsuarioEstudoGateway usuarioEstudoGateway;

	@Test
	public void cadastrarEstudoUsuarioSucesso() {
		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastrarUsuarioEstudo cadastrarUsuarioEstudo = new CadastrarUsuarioEstudoImpl(
				this.usuarioEstudoGateway);
		cadastrarUsuarioEstudo.execute(email, nomeEstudo, null);
	}

	@Test(expected = UsuarioEstudoJaExiste.class)
	public void cadastrarEstudoUsuario_Falha_EstudoJaExiste() {
		doThrow(new UsuarioEstudoJaExiste()).when(this.usuarioEstudoGateway)
				.cadastrar(anyString(), anyString(), any(Date.class));

		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastrarUsuarioEstudo cadastrarUsuarioEstudo = new CadastrarUsuarioEstudoImpl(
				this.usuarioEstudoGateway);
		cadastrarUsuarioEstudo.execute(email, nomeEstudo, null);
	}

}
