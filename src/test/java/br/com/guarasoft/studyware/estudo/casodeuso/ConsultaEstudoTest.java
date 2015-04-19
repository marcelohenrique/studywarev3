package br.com.guarasoft.studyware.estudo.casodeuso;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.estudo.gateway.EstudoGateway;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@RunWith(MockitoJUnitRunner.class)
public class ConsultaEstudoTest {

	private @Mock EstudoGateway estudoGateway;

	@Test
	public void testConsultaEstudos() {
		Usuario usuario = new Usuario();

		ConsultaEstudo consultaEstudo = new ConsultaEstudoImpl(estudoGateway);

		Collection<Estudo> estudos = consultaEstudo.consulta(usuario);

		assertNotNull(estudos);
	}

}
