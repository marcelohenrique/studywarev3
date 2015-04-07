package br.com.guarasoft.studyware.estudo.casodeuso;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.estudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarEstudoTest {

	@Mock
	private EstudoGateway usuarioEstudoGateway;

	@Test
	public void cadastrarEstudoSucesso() {
		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastrarEstudo cadastrarUsuarioEstudo = new CadastrarEstudo(this.usuarioEstudoGateway);

		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		Estudo estudo = new Estudo(nomeEstudo, usuario, null);

		cadastrarUsuarioEstudo.execute(estudo);
	}

	@Test(expected = UsuarioEstudoJaExiste.class)
	public void cadastrarEstudo_Falha_EstudoJaExiste() {
		doThrow(new UsuarioEstudoJaExiste()).when(this.usuarioEstudoGateway).cadastrar(any(Estudo.class));

		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastrarEstudo cadastrarUsuarioEstudo = new CadastrarEstudo(this.usuarioEstudoGateway);

		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		Estudo estudo = new Estudo(nomeEstudo, usuario, null);

		cadastrarUsuarioEstudo.execute(estudo);
	}

}
