package br.com.guarasoft.studyware.estudodiario.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Dia {
	@Id
	private Long id;
	private String nome;
	private String descricao;
}
