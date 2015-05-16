package br.com.guarasoft.studyware.estudomateriahistorico.modelo;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.materia.modelo.Materia;

@Data
public class ResumoMateria {

	private Materia materia;
	private Duration tempoAlocado;
	private Duration somaTempo;

	public double getCiclo() {
		if (tempoAlocado.isEqual(Duration.ZERO)) {
			return 0.0;
		}
		return ((double) somaTempo.getMillis() / (double) tempoAlocado
				.getMillis());
	}

}
