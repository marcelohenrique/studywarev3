package br.com.guarasoft.studyware.estudo.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.guarasoft.studyware.estudodiario.modelo.EstudoDiario;
import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

//@Data
public class Estudo {

	private String nome;
	private Usuario dono;
	// private DateTime inicio;
	private Date fim;

	private Collection<Usuario> usuarios = new ArrayList<Usuario>();

	private List<EstudoMateria> materias = new ArrayList<EstudoMateria>();
	private List<EstudoDiario> dias = new ArrayList<EstudoDiario>();

	public Estudo(String nome, Usuario dono, Date fim) {
		this.nome = nome;
		this.dono = dono;
		this.fim = fim;
	}

	public void add(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public void add(EstudoDiario estudoDiario) {
		this.dias.add(estudoDiario);
	}

	public void add(EstudoMateria estudoMateria) {
		this.materias.add(estudoMateria);
	}

	public String getNome() {
		return nome;
	}

	public Date getFim() {
		return fim;
	}

	public Collection<Usuario> getUsuarios() {
		return Collections.unmodifiableCollection(usuarios);
	}

	public List<EstudoMateria> getMaterias() {
		return Collections.unmodifiableList(materias);
	}

	public List<EstudoDiario> getDias() {
		return Collections.unmodifiableList(dias);
	}

}
