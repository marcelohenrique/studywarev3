package br.com.guarasoft.studyware.usuario.modelo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Usuario implements Serializable {

	private static final long serialVersionUID = 2911216730469825660L;

	private String email;
	private boolean ativo;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (this.email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.email == null) ? 0 : this.email.hashCode());
		return result;
	}

}
