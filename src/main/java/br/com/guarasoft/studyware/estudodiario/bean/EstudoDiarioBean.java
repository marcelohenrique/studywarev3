package br.com.guarasoft.studyware.estudodiario.bean;

import java.util.Date;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.Dia;

@Data
public class EstudoDiarioBean {

	private Date inicioSemana;
	private Dia diaSemana;
	private Duration tempoAlocado;
	private Duration tempoEstudado;

}
