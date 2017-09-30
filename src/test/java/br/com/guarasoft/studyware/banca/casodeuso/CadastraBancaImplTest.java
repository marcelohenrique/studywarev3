package br.com.guarasoft.studyware.banca.casodeuso;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.guarasoft.studyware.banca.gateway.BancaGateway;
import br.com.guarasoft.studyware.banca.modelo.Banca;

@RunWith(MockitoJUnitRunner.class)
public class CadastraBancaImplTest {

	private @Mock BancaGateway bancaGateway;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCadastrar() {
		CadastraBanca cadastraBanca = new CadastraBancaImpl(this.bancaGateway);

		Banca banca = new Banca();

		cadastraBanca.cadastrar(banca);
	}

}
