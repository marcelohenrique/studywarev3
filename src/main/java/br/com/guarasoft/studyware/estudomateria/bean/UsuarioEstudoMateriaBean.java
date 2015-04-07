package br.com.guarasoft.studyware.estudomateria.bean;

import lombok.Data;
import lombok.ToString;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;

@Data
@ToString(exclude = { "estudo" })
public class UsuarioEstudoMateriaBean {

	private Long id;
	private Estudo estudo;
	private MateriaBean materia;
	private Duration tempoAlocado;
	private Long ordem;

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
		UsuarioEstudoMateriaBean other = (UsuarioEstudoMateriaBean) obj;
		if (this.materia == null) {
			if (other.materia != null) {
				return false;
			}
		} else if (!this.materia.equals(other.materia)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.materia == null) ? 0 : this.materia.hashCode());
		return result;
	}

}
