package br.com.guarasoft.studyware.usuarioestudo.casodeuso;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
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

		CadastrarUsuarioEstudo cadastrarUsuarioEstudo = new CadastrarUsuarioEstudoImpl(this.usuarioEstudoGateway);

		UsuarioEstudoBean usuarioEstudoBean = new UsuarioEstudoBean();
		usuarioEstudoBean.setEmail(email);
		usuarioEstudoBean.setNome(nomeEstudo);

		cadastrarUsuarioEstudo.execute(usuarioEstudoBean);
	}

	@Test(expected = UsuarioEstudoJaExiste.class)
	public void cadastrarEstudoUsuario_Falha_EstudoJaExiste() {
		doThrow(new UsuarioEstudoJaExiste()).when(this.usuarioEstudoGateway).cadastrar(any(UsuarioEstudoBean.class));

		String email = "teste@gmail.com";
		String nomeEstudo = "Estudo Teste";

		CadastrarUsuarioEstudo cadastrarUsuarioEstudo = new CadastrarUsuarioEstudoImpl(this.usuarioEstudoGateway);

		UsuarioEstudoBean usuarioEstudoBean = new UsuarioEstudoBean();
		usuarioEstudoBean.setEmail(email);
		usuarioEstudoBean.setNome(nomeEstudo);

		cadastrarUsuarioEstudo.execute(usuarioEstudoBean);
	}

}
