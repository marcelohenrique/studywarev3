package br.com.guarasoft.studyware.estudomateriahistorico.modelo;

import lombok.Data;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.materia.modelo.Materia;

@Data
public class ResumoMateria {

	private Materia materia;
	private Duration somaTempo;
	// private Long qtd;
	// private String observacao;

}
