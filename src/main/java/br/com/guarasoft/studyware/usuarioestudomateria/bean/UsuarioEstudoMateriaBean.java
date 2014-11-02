package br.com.guarasoft.studyware.usuarioestudomateria.bean;

import java.util.Date;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

@Data
public class UsuarioEstudoMateriaBean {

	private UsuarioEstudoBean usuarioEstudoBean;
	private MateriaBean materiaBean;
	private Duration tempoAlocado;
	private Long ordem;

	public Date getTempoAlocadoDate() {
		return new Date(this.tempoAlocado.getMillis());
	}

	public void setTempoAlocadoDate(Date date) {
		this.tempoAlocado = new Duration(date);
	}
}
