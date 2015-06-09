package br.com.guarasoft.studyware.estudosemanal.bean;

import lombok.Getter;

public enum IntervaloEstudo {
	DIARIO(1), SEMANAL(6);
	@Getter
	private int dias;
	private IntervaloEstudo(int dias) {
		this.dias = dias;
	}
}
