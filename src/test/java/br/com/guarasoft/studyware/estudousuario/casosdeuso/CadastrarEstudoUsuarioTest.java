package br.com.guarasoft.studyware.estudousuario.casosdeuso;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.estudousuario.excecoes.EstudoJaExiste;
import br.com.guarasoft.studyware.estudousuario.gateway.EstudoUsuarioGateway;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarEstudoUsuarioTest {

	@Mock
	private EstudoUsuarioGateway estudoUsuarioGateway;

	@Test
	public void cadastrarEstudoUsuarioSucesso() {
		when(this.estudoUsuarioGateway.cadastrar(anyString(), anyString()))
				.thenReturn(Boolean.TRUE);

		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastrarEstudoUsuario cadastrarEstudoUsuario = new CadastrarEstudoUsuarioImpl(
				this.estudoUsuarioGateway);
		Boolean sucesso = cadastrarEstudoUsuario.execute(email, nomeEstudo);

		assertTrue(sucesso);
	}

	@Test(expected = EstudoJaExiste.class)
	public void cadastrarEstudoUsuario_Falha_EstudoJaExiste() {
		when(this.estudoUsuarioGateway.cadastrar(anyString(), anyString()))
				.thenThrow(new EstudoJaExiste());

		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastrarEstudoUsuario cadastrarEstudoUsuario = new CadastrarEstudoUsuarioImpl(
				this.estudoUsuarioGateway);
		cadastrarEstudoUsuario.execute(email, nomeEstudo);
	}

}
