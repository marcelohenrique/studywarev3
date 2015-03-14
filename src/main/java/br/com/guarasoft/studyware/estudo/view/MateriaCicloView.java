package br.com.guarasoft.studyware.estudo.view;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import br.com.guarasoft.studyware.controller.converter.DurationConverter;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;

@Data
public class MateriaCicloView {

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private DurationConverter durationConverter = new DurationConverter();

	private UsuarioEstudoMateriaBean materia;

	public Date getTempoAlocado() {
		return this.durationConverter.toDate(this.materia.getTempoAlocado());
	}

	public void setTempoAlocado(Date tempoAlocado) {
		this.materia.setTempoAlocado(this.durationConverter.toDuration(tempoAlocado));
	}

}
