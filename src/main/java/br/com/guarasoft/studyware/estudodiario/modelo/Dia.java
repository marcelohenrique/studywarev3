package br.com.guarasoft.studyware.estudodiario.modelo;

public enum Dia {

	DOMINGO(0, "Domingo", "Domingo"), //
	SEGUNDA(1, "Segunda", "Segunda-feira"), //
	TERCA(2, "Terça", "Terça-feira"), //
	QUARTA(3, "Quarta", "Quarta-feira"), //
	QUINTA(4, "Quinta", "Quinta-feira"), //
	SEXTA(5, "Sexta", "Sexta-feira"), //
	SABADO(6, "Sábado", "Sábado");

	private int id;
	private String nome;
	private String descricao;

	private Dia(int id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
