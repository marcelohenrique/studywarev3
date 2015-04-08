package br.com.guarasoft.studyware.estudodiario.modelo;

import java.util.Date;

import lombok.Data;

import org.joda.time.Duration;

@Data
public class EstudoSemanal {

	private Date inicioSemana;
	private Dia diaSemana;
	private Duration tempoAlocado;
	private Duration tempoEstudado;

}
