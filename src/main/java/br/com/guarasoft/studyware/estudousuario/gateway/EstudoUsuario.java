package br.com.guarasoft.studyware.estudousuario.gateway;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EstudoUsuario {

	@EmbeddedId
	private EstudoUsuarioPK estudodoEstudoUsuarioPK;
	private String nome;
	private Date fim;

	public EstudoUsuarioPK getEstudodoEstudoUsuarioPK() {
		return estudodoEstudoUsuarioPK;
	}

	public void setEstudodoEstudoUsuarioPK(
			EstudoUsuarioPK estudodoEstudoUsuarioPK) {
		this.estudodoEstudoUsuarioPK = estudodoEstudoUsuarioPK;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

}
