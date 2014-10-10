package br.com.guarasoft.studyware.usuario;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.guarasoft.studyware.usuario.gateway.UsuarioGateway;

public class UsuarioTest {

	private UsuarioGateway usuarioGateway;

	@Test
	public void autenticarUsuarioValido() {
		String email = "mhguara@gmail.com";
		Usuario usuario = new Usuario(this.usuarioGateway, email);
		Boolean autentico = usuario.autenticar();
		assertTrue(autentico);
	}

	@Test
	public void autenticarUsuarioInvalido() {
		String email = "mhguara@gmail.com";
		Usuario usuario = new Usuario(this.usuarioGateway, email);
		Boolean autentico = usuario.autenticar();
		assertFalse(autentico);
	}

}
