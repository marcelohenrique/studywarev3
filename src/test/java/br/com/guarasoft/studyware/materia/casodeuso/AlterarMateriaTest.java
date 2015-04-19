package br.com.guarasoft.studyware.materia.casodeuso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.materia.modelo.Materia;

@RunWith(MockitoJUnitRunner.class)
public class AlterarMateriaTest {

	@Mock
	private MateriaGateway materiaGateway;

	@Test
	public void alterarMateriaSucesso() {
		AlterarMateria alterarMateria = new AlterarMateriaImpl(this.materiaGateway);

		Materia materiaAlterada = new Materia();

		alterarMateria.execute(materiaAlterada);
	}

}
