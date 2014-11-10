package br.com.guarasoft.studyware.materia.casodeuso;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.excecao.CampoObrigatorioNaoInformado;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;

@RunWith(MockitoJUnitRunner.class)
public class CadastrarMateriaTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Mock
	private MateriaGateway materiaGateway;

	@Test
	public void testCadastrarMateriaSucessso() {
		CadastrarMateria cadastrarMateria = new CadastrarMateriaImpl(this.materiaGateway);

		String sigla = "DEV";
		String nome = "Desenvolvimento";

		cadastrarMateria.execute(sigla, nome);
	}

	@Test
	public void testCadastrarMateriaSemSigla() {
		CadastrarMateria cadastrarMateria = new CadastrarMateriaImpl(this.materiaGateway);

		String sigla = null;
		String nome = null;

		this.expectedException.expect(CampoObrigatorioNaoInformado.class);
		this.expectedException.expectMessage("O campo Sigla é obrigatório");
		cadastrarMateria.execute(sigla, nome);
	}

	@Test
	public void testCadastrarMateriaSemNome() {
		CadastrarMateria cadastrarMateria = new CadastrarMateriaImpl(this.materiaGateway);

		String sigla = "DEV";
		String nome = null;

		this.expectedException.expect(CampoObrigatorioNaoInformado.class);
		this.expectedException.expectMessage("O campo Nome é obrigatório");
		cadastrarMateria.execute(sigla, nome);
	}

}
