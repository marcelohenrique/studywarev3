package br.com.guarasoft.studyware.usuarioestudo.casodeuso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.estudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.estudo.casodeuso.RemoverUsuarioEstudo;
import br.com.guarasoft.studyware.estudo.casodeuso.RemoverUsuarioEstudoImpl;
import br.com.guarasoft.studyware.estudo.gateway.UsuarioEstudoGateway;

@RunWith(MockitoJUnitRunner.class)
public class RemoverUsuarioEstudoTest {

	@Mock
	private UsuarioEstudoGateway usuarioEstudoGateway;

	@Test
	public void execute() {
		UsuarioEstudoBean usuarioEstudo = new UsuarioEstudoBean();

		RemoverUsuarioEstudo removerUsuarioEstudo = new RemoverUsuarioEstudoImpl(this.usuarioEstudoGateway);
		removerUsuarioEstudo.execute(usuarioEstudo);
	}

}
