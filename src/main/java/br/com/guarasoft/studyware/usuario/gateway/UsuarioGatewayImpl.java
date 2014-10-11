package br.com.guarasoft.studyware.usuario.gateway;

import br.com.guarasoft.studyware.usuario.entidades.Usuario;
import br.com.guarasoft.studyware.usuario.entidades.UsuarioImpl;

public class UsuarioGatewayImpl implements UsuarioGateway {

	@Override
	public Usuario pesquisaPorEmail(String email) {
		if ("mhguara@gmail.com".equals(email)) {
			return new UsuarioImpl();
		}
		return null;
	}

}
