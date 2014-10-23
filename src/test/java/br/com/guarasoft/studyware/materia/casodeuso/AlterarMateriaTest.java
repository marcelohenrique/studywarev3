package br.com.guarasoft.studyware.materia.casodeuso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;

@RunWith(MockitoJUnitRunner.class)
public class AlterarMateriaTest {

	@Mock
	private MateriaGateway materiaGateway;

	@Test
	public void alterarMateriaSucesso() {
		AlterarMateria alterarMateria = new AlterarMateriaImpl(this.materiaGateway);

		MateriaBean materiaAlterada = new MateriaBean();

		alterarMateria.execute(materiaAlterada);
	}

}
