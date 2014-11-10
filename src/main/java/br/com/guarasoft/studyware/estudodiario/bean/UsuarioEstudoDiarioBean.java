package br.com.guarasoft.studyware.estudodiario.bean;

import lombok.Data;
import lombok.ToString;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

@Data
@ToString(exclude = { "usuarioEstudo" })
public class UsuarioEstudoDiarioBean {

	private UsuarioEstudoBean usuarioEstudo;
	private DiaBean dia;
	private Duration tempoAlocado;

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
		UsuarioEstudoDiarioBean other = (UsuarioEstudoDiarioBean) obj;
		if (this.dia != other.dia) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.dia == null) ? 0 : this.dia.hashCode());
		return result;
	}

}
