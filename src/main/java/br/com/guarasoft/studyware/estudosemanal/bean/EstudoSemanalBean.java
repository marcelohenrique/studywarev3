package br.com.guarasoft.studyware.estudosemanal.bean;

import java.util.Date;

import lombok.Data;

import org.joda.time.DateTime;
import org.joda.time.Duration;

@Data
public class EstudoSemanalBean {

	private Date inicioSemana;
	private Duration tempoEstudado;
	private Duration tempoEstudadoAcumulado;
	
	private IntervaloEstudo intervalo;

	public Date getDataFimSemana() {
		return new DateTime(this.inicioSemana).plusDays(intervalo.getDias()).toDate();
	}

}
