package br.com.guarasoft.studyware.estudodiario.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "Dia")
@Table(name = "Dia")
@Data
public class DiaEntidade {
	@Id
	private Long id;
	private String nome;
	private String descricao;
}
