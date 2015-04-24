package br.com.guarasoft.studyware.estudomateriahistorico.modelo;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;

@Data
public class ResumoEstudoMateria {

	private EstudoMateria estudoMateria;
	private Duration somaTempo;

}
