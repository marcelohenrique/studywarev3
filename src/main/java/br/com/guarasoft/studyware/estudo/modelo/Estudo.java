package br.com.guarasoft.studyware.estudo.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import br.com.guarasoft.studyware.estudodiario.modelo.EstudoDiario;
import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class Estudo {

	private Long id;
	private String nome;
	private Date inicio;
	private Date fim;

	private Collection<EstudoMateria> materias = new ArrayList<>();
	private Collection<EstudoDiario> dias = new TreeSet<>();

	private Collection<Usuario> participantes = new ArrayList<>();

	public Estudo(Long id, String nome, Date inicio, Date fim) {
		this.id = id;
		this.nome = nome;
		this.inicio = inicio;
		this.fim = fim;
	}

	public Estudo(Long id, String nome, Date inicio, Date fim,
			Collection<EstudoMateria> materias,
			Collection<EstudoDiario> semana, Collection<Usuario> participantes) {
		this(id, nome, inicio, fim);
		this.materias = materias;
		this.atualizaOrdemCiclo();
		this.dias = semana;
		this.participantes = participantes;
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

	public Date getInicio() {
		return this.inicio;
	}

	public Date getFim() {
		return this.fim;
	}

	public Collection<Usuario> getParticipantes() {
		return Collections.unmodifiableCollection(this.participantes);
	}

	public Collection<EstudoMateria> getMaterias() {
		return Collections.unmodifiableCollection(this.materias);
	}

	public Collection<EstudoDiario> getDias() {
		return Collections.unmodifiableCollection(this.dias);
	}

}
