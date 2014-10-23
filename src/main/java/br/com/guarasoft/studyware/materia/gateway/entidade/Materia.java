package br.com.guarasoft.studyware.materia.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materiaSeq")
	@SequenceGenerator(name = "materiaSeq", sequenceName = "materiaSeq", allocationSize = 1)
	private Long id;
	private String sigla;
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
