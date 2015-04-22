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
public class CadastraEstudoTest {

	@Mock
	private EstudoGateway usuarioEstudoGateway;

	@Test
	public void cadastrarEstudoSucesso() {
		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastraEstudo cadastrarUsuarioEstudo = new CadastraEstudo(this.usuarioEstudoGateway);

		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		Estudo estudo = new Estudo(null, nomeEstudo, null);

		cadastrarUsuarioEstudo.execute(estudo);
	}

	@Test(expected = UsuarioEstudoJaExiste.class)
	public void cadastrarEstudo_Falha_EstudoJaExiste() {
		doThrow(new UsuarioEstudoJaExiste()).when(this.usuarioEstudoGateway).cadastrar(any(Estudo.class));

		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastraEstudo cadastrarUsuarioEstudo = new CadastraEstudo(this.usuarioEstudoGateway);

		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		Estudo estudo = new Estudo(null, nomeEstudo, null);

		cadastrarUsuarioEstudo.execute(estudo);
	}

}
