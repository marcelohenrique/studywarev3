package br.com.guarasoft.studyware.materia.gateway.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

@Entity
public class Materia implements Entidade {

	private static final long serialVersionUID = -8089662973205060676L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materiaSeq")
	@SequenceGenerator(name = "materiaSeq", sequenceName = "materiaSeq", allocationSize = 1)
	private Long id;
	private String sigla;
	private String nome;
	@OneToMany(mappedBy = "pk.materia")
	private List<UsuarioEstudoMateria> usuarioEstudoMaterias;

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
