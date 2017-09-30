package br.com.guarasoft.studyware.banca.gateway.converter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import br.com.guarasoft.studyware.banca.gateway.entidade.BancaEntidade;
import br.com.guarasoft.studyware.banca.modelo.Banca;

public class BancaBuilderTest {

	private static final String TESTE = "TESTE";

	@Test
	public void testConvertEntidade() {
		BancaEntidade entidade = createBancaEntidade();

		BancaBuilder builder = new BancaBuilder();
		Banca bean = builder.convert(entidade);

		assertBanca(bean);
	}

	@Test
	public void testConvertEntidades() {
		Collection<BancaEntidade> entidades = new ArrayList<>();
		entidades.add(createBancaEntidade());
		entidades.add(createBancaEntidade());
		entidades.add(createBancaEntidade());

		BancaBuilder builder = new BancaBuilder();
		Collection<Banca> bancas = builder.convert(entidades);

		assertNotNull(bancas);
		assertFalse(bancas.isEmpty());
		assertEquals(entidades.size(), bancas.size());
		for (Banca banca : bancas) {
			assertBanca(banca);
		}
	}

	@Test
	public void testConvert_Bean2Entidade() {
		Banca banca = new Banca();
		banca.setNome(TESTE);
		banca.setDescricao(TESTE);
		banca.setSite(TESTE);

		BancaBuilder builder = new BancaBuilder();
		BancaEntidade entidade = builder.convert(banca);

		assertNotNull(entidade);
		assertEquals(TESTE, entidade.getNome());
		assertEquals(TESTE, entidade.getDescricao());
		assertEquals(TESTE, entidade.getSite());
	}

	private BancaEntidade createBancaEntidade() {
		BancaEntidade entidade = new BancaEntidade();
		entidade.setNome(TESTE);
		entidade.setDescricao(TESTE);
		entidade.setSite(TESTE);
		return entidade;
	}

	private void assertBanca(Banca banca) {
		assertNotNull(banca);
		assertEquals(TESTE, banca.getNome());
		assertEquals(TESTE, banca.getDescricao());
		assertEquals(TESTE, banca.getSite());
	}

}
