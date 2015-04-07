package br.com.guarasoft.studyware.estudo.casodeuso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.estudo.casodeuso.RemoverUsuarioEstudo;
import br.com.guarasoft.studyware.estudo.casodeuso.RemoverUsuarioEstudoImpl;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;

@RunWith(MockitoJUnitRunner.class)
public class RemoverUsuarioEstudoTest {

	@Mock
	private EstudoGateway usuarioEstudoGateway;

	@Test
	public void execute() {
		Estudo usuarioEstudo = new Estudo(null, null, null);

		RemoverUsuarioEstudo removerUsuarioEstudo = new RemoverUsuarioEstudoImpl(this.usuarioEstudoGateway);
		removerUsuarioEstudo.execute(usuarioEstudo);
	}

}
