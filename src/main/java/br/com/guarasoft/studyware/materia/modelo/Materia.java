package br.com.guarasoft.studyware.materia.modelo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Materia implements Serializable {

	private static final long serialVersionUID = -5779053744569232838L;

	private Long id;
	private String sigla;
	private String nome;

}
