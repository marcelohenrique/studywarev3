package br.com.guarasoft.studyware.estudo.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.usuario.gateway.entidade.UsuarioEntity;

@Entity
@Table(name = "UsuarioEstudo")
@Data
public class UsuarioEstudoEntidade implements Entidade {

	private static final long serialVersionUID = 1502275345514321869L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsuarioEstudoSeq")
	@SequenceGenerator(name = "UsuarioEstudoSeq", sequenceName = "UsuarioEstudoSeq", allocationSize = 1)
	private Long id;

	@JoinColumn(name = "usuario", referencedColumnName = "email")
	@ManyToOne
	private UsuarioEntity usuario;

	@ManyToOne
	@JoinColumn(name = "estudo")
	private EstudoEntidade estudo;

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
		UsuarioEstudoEntidade other = (UsuarioEstudoEntidade) obj;
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
