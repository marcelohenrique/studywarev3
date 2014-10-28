package br.com.guarasoft.studyware.materia.casodeuso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;

@RunWith(MockitoJUnitRunner.class)
public class RemoverMateriaTest {

	@Mock
	private MateriaGateway materiaGateway;

	@Test
	public void test() {
		RemoverMateria removerMateria = new RemoverMateriaImpl(
				this.materiaGateway);

		MateriaBean materia = new MateriaBean();

		removerMateria.execute(materia);
	}

}
