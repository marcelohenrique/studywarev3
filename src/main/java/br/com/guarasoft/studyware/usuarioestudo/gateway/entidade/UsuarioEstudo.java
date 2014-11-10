package br.com.guarasoft.studyware.usuarioestudo.gateway.entidade;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.UsuarioEstudoDiario;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

@Entity
@Data
public class UsuarioEstudo implements Entidade {

	private static final long serialVersionUID = 1502275345514321869L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsuarioEstudoSeq")
	@SequenceGenerator(name = "UsuarioEstudoSeq", sequenceName = "UsuarioEstudoSeq", allocationSize = 1)
	private Long id;

	private String email;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Date fim;

	@OneToMany(mappedBy = "usuarioEstudo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	@OrderBy("ordem")
	private Set<UsuarioEstudoMateria> materias;

	@OneToMany(mappedBy = "pk.usuarioEstudo", cascade = { CascadeType.ALL })
	private List<UsuarioEstudoDiario> usuarioEstudoDiarios;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		UsuarioEstudo other = (UsuarioEstudo) obj;
		if (this.email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!this.email.equals(other.email)) {
			return false;
		}
		if (this.nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!this.nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
		result = prime * result + ((this.nome == null) ? 0 : this.nome.hashCode());
		return result;
	}

}
