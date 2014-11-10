package br.com.guarasoft.studyware.usuarioestudo.casodeuso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.UsuarioEstudoGateway;

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
