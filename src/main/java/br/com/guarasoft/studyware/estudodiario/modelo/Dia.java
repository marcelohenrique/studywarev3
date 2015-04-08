package br.com.guarasoft.studyware.estudodiario.modelo;

public enum Dia {

	DOMINGO("Domingo", "Domingo"), //
	SEGUNDA("Segunda", "Segunda-feira"), //
	TERCA("Terça", "Terça-feira"), //
	QUARTA("Quarta", "Quarta-feira"), //
	QUINTA("Quinta", "Quinta-feira"), //
	SEXTA("Sexta", "Sexta-feira"), //
	SABADO("Sábado", "Sábado");

	private String nome;
	private String descricao;

	private Dia(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
