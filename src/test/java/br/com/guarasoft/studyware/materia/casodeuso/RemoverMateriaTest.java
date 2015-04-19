package br.com.guarasoft.studyware.materia.casodeuso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;
import br.com.guarasoft.studyware.materia.modelo.Materia;

@RunWith(MockitoJUnitRunner.class)
public class RemoverMateriaTest {

	@Mock
	private MateriaGateway materiaGateway;

	@Test
	public void test() {
		RemoverMateria removerMateria = new RemoverMateriaImpl(this.materiaGateway);

		Materia materia = new Materia();

		removerMateria.execute(materia);
	}

}
