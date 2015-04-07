package br.com.guarasoft.studyware.estudo.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;

import br.com.guarasoft.studyware.estudodiario.bean.UsuarioEstudoDiarioBean;
import br.com.guarasoft.studyware.estudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

//@Data
public class Estudo {

	private String nome;
	private Usuario dono;
	// private DateTime inicio;
	private DateTime fim;

	private Collection<Usuario> usuarios = new ArrayList<Usuario>();

	private List<UsuarioEstudoMateriaBean> materias;
	private List<UsuarioEstudoDiarioBean> dias;

	public Estudo(String nome, Usuario dono, DateTime fim) {
		this.nome = nome;
		this.dono = dono;
		this.fim = fim;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public DateTime getFim() {
		return fim;
	}

	public void setFim(DateTime fim) {
		this.fim = fim;
	}

	public void add(Usuario usuario) {
		this.usuarios.add(usuario);
	}

	public Collection<Usuario> getUsuarios() {
		return Collections.unmodifiableCollection(usuarios);
	}

	public List<UsuarioEstudoMateriaBean> getMaterias() {
		return materias;
	}

	public void setMaterias(List<UsuarioEstudoMateriaBean> materias) {
		this.materias = materias;
	}

	public List<UsuarioEstudoDiarioBean> getDias() {
		return dias;
	}

	public void setDias(List<UsuarioEstudoDiarioBean> dias) {
		this.dias = dias;
	}

}
