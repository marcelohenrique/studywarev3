package br.com.guarasoft.studyware.usuarioestudo.casodeuso;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.estudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.estudo.casodeuso.CadastrarUsuarioEstudo;
import br.com.guarasoft.studyware.estudo.casodeuso.CadastrarUsuarioEstudoImpl;
import br.com.guarasoft.studyware.estudo.excecao.UsuarioEstudoJaExiste;
import br.com.guarasoft.studyware.estudo.gateway.UsuarioEstudoGateway;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

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
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuarioEstudoBean.setUsuario(usuario);
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
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuarioEstudoBean.setUsuario(usuario);
		usuarioEstudoBean.setNome(nomeEstudo);

		cadastrarUsuarioEstudo.execute(usuarioEstudoBean);
	}

}
