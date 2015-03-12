package br.com.guarasoft.studyware.usuarioestudo.gateway.entidade;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.UsuarioEstudoDiario;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.usuario.gateway.entidade.UsuarioEntity;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

@Entity
@Data
public class UsuarioEstudo implements Entidade {

	private static final long serialVersionUID = 1502275345514321869L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsuarioEstudoSeq")
	@SequenceGenerator(name = "UsuarioEstudoSeq", sequenceName = "UsuarioEstudoSeq", allocationSize = 1)
	private Long id;

	@JoinColumn(name = "email", referencedColumnName = "email")
	@ManyToOne
	private UsuarioEntity usuario;

	private String nome;

	@Temporal(TemporalType.DATE)
	private Date fim;

	@OneToMany(mappedBy = "usuarioEstudo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	@OrderBy("ordem")
	private Set<UsuarioEstudoMateria> materias;

	@OneToMany(mappedBy = "usuarioEstudo", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Set<UsuarioEstudoDiario> usuarioEstudoDiarios;

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
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

}
