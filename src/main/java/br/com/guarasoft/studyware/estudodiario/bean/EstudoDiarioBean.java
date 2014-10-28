package br.com.guarasoft.studyware.estudodiario.bean;

import java.util.Date;

import lombok.Data;

import org.joda.time.Duration;

@Data
public class EstudoDiarioBean {

	private Date inicioSemana;
	private DiaBean diaSemana;
	private Duration tempoAlocado;
	private Duration tempoEstudado;

}
