package br.com.guarasoft.studyware.estudodiario.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;

@Data
@ToString(exclude = { "estudo" })
@EqualsAndHashCode(of = { "estudo", "dia" })
public class EstudoDiario implements Comparable<EstudoDiario> {

	private Long id;
	private Estudo estudo;
	private Dia dia;
	private Duration tempoAlocado;

	@Override
	public int compareTo(EstudoDiario o) {
		return dia.getId() - o.getDia().getId();
	}

}
