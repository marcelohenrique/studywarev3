package br.com.guarasoft.studyware.estudousuario.gateway;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EstudoUsuario {

	@EmbeddedId
	private EstudoUsuarioPK estudoUsuarioPK;
	private Date fim;

	public EstudoUsuarioPK getEstudoUsuarioPK() {
		return estudoUsuarioPK;
	}

	public void setEstudoUsuarioPK(EstudoUsuarioPK estudoUsuarioPK) {
		this.estudoUsuarioPK = estudoUsuarioPK;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

}
