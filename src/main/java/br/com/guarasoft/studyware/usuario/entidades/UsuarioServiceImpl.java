package br.com.guarasoft.studyware.usuario.entidades;

public class UsuarioServiceImpl implements UsuarioService {

	private String email;

	public UsuarioServiceImpl(String email) {
		this.email = email;
	}

	@Override
	public String getEmail() {
		return email;
	}

}
