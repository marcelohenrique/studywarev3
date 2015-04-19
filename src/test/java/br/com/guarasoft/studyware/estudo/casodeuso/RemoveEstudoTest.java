package br.com.guarasoft.studyware.estudo.casodeuso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.estudo.casodeuso.RemoveEstudo;
import br.com.guarasoft.studyware.estudo.casodeuso.RemoveEstudoImpl;
import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;

@RunWith(MockitoJUnitRunner.class)
public class RemoveEstudoTest {

	@Mock
	private EstudoGateway usuarioEstudoGateway;

	@Test
	public void execute() {
		Estudo usuarioEstudo = new Estudo(null, null, null);

		RemoveEstudo removeEstudo = new RemoveEstudoImpl(this.usuarioEstudoGateway);
		removeEstudo.execute(usuarioEstudo);
	}

}
