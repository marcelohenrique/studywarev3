package br.com.guarasoft.studyware.estudo.modelo;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class EstudoTest {

	@Test
	public void testCriaEstudo() {
		String nome = "Estudo";
		Usuario dono = new Usuario();
		DateTime fim = new DateTime(new Date());

		Estudo estudo = new Estudo(nome, dono, fim);

		assertNotNull(estudo);
	}

}
