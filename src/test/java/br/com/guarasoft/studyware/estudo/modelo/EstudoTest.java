package br.com.guarasoft.studyware.estudo.modelo;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

public class EstudoTest {

	@Test
	public void testCriaEstudo() {
		String nome = "Estudo";
		Date fim = new Date();

		Estudo estudo = new Estudo(null, nome, fim);

		assertNotNull(estudo);
	}

}
