package br.com.guarasoft.studyware.estudomateriahistorico.modelo;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;

@Data
public class ResumoEstudoMateria {

	private EstudoMateria estudoMateria;
	private Duration somaTempo;

	public double getCiclo() {
		if (estudoMateria.getTempoAlocado().isEqual(Duration.ZERO)) {
			return 0.0;
		}
		return ((double) somaTempo.getMillis() / (double) estudoMateria
				.getTempoAlocado().getMillis());
	}

}
