package br.com.guarasoft.studyware.estudousuario.casosdeuso;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;

import java.util.Date;

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
		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastrarEstudoUsuario cadastrarEstudoUsuario = new CadastrarEstudoUsuarioImpl(
				this.estudoUsuarioGateway);
		cadastrarEstudoUsuario.execute(email, nomeEstudo, null);
	}

	@Test(expected = EstudoJaExiste.class)
	public void cadastrarEstudoUsuario_Falha_EstudoJaExiste() {
		doThrow(new EstudoJaExiste()).when(this.estudoUsuarioGateway)
				.cadastrar(anyString(), anyString(), any(Date.class));

		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastrarEstudoUsuario cadastrarEstudoUsuario = new CadastrarEstudoUsuarioImpl(
				this.estudoUsuarioGateway);
		cadastrarEstudoUsuario.execute(email, nomeEstudo, null);
	}

}
