package br.com.guarasoft.studyware.materia.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = { "id" })
@ToString(of = { "id" })
public class Materia {

	private Long id;
	private String sigla;
	private String nome;

}
