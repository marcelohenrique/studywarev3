package br.com.guarasoft.studyware.estudo.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.guarasoft.studyware.estudodiario.modelo.EstudoDiario;
import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class Estudo {

	private Long id;
	private String nome;
	// private DateTime inicio;
	private Date fim;

	private Collection<Usuario> participantes = new ArrayList<Usuario>();

	private List<EstudoMateria> materias = new ArrayList<EstudoMateria>();
	private List<EstudoDiario> dias = new ArrayList<EstudoDiario>();

	public Estudo(Long id, String nome, Date fim) {
		this.id = id;
		this.nome = nome;
		this.fim = fim;
	}

	public Estudo(Long id, String nome, Date fim, List<EstudoMateria> materias) {
		this(id, nome, fim);
		this.materias = materias;
		this.atualizaOrdemCiclo();
	}

	public void add(Usuario usuario) {
		this.participantes.add(usuario);
	}

	public void add(EstudoDiario estudoDiario) {
		this.dias.add(estudoDiario);
	}

	public void add(EstudoMateria estudoMateria) {
		this.materias.add(estudoMateria);
	}

	private void atualizaOrdemCiclo() {
		Long ordem = 1L;
		for (EstudoMateria estudoMateria : this.materias) {
			estudoMateria.setOrdem(ordem++);
		}
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public Date getFim() {
		return this.fim;
	}

	public Collection<Usuario> getParticipantes() {
		return Collections.unmodifiableCollection(this.participantes);
	}

	public List<EstudoMateria> getMaterias() {
		return Collections.unmodifiableList(this.materias);
	}

	public List<EstudoDiario> getDias() {
		return Collections.unmodifiableList(this.dias);
	}

}
