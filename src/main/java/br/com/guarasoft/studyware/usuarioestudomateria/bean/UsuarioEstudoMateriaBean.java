package br.com.guarasoft.studyware.usuarioestudomateria.bean;

import lombok.Data;
import lombok.ToString;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

@Data
@ToString(exclude = { "usuarioEstudo" })
public class UsuarioEstudoMateriaBean {

	private Long id;
	private UsuarioEstudoBean usuarioEstudo;
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
