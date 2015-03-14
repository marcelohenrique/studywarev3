package br.com.guarasoft.studyware.estudo.view;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import br.com.guarasoft.studyware.controller.converter.DurationConverter;
import br.com.guarasoft.studyware.estudodiario.bean.UsuarioEstudoDiarioBean;

@Data
public class EstudoDiaView {

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private DurationConverter durationConverter = new DurationConverter();

	private UsuarioEstudoDiarioBean estudoDiario;

	public Date getTempoAlocado() {
		return this.durationConverter.toDate(this.estudoDiario.getTempoAlocado());
	}

	public void setTempoAlocado(Date tempoAlocado) {
		this.estudoDiario.setTempoAlocado(this.durationConverter.toDuration(tempoAlocado));
	}

}
