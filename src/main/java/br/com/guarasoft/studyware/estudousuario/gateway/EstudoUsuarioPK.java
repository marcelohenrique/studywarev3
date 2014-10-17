package br.com.guarasoft.studyware.estudousuario.gateway;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Embeddable
public class EstudoUsuarioPK implements Serializable {

	private static final long serialVersionUID = -3665744009712951223L;

	private String email;
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EstudoUsuarioSeq")
	@SequenceGenerator(name = "EstudoUsuarioSeq", sequenceName = "EstudoUsuarioSeq", allocationSize = 1)
	private Long id;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstudoUsuarioPK other = (EstudoUsuarioPK) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
