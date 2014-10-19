package br.com.guarasoft.studyware.estudousuario.gateway.entidade;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class EstudoUsuario {

	@EmbeddedId
	private EstudoUsuarioPK estudoUsuarioPK;
	@Temporal(TemporalType.DATE)
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
