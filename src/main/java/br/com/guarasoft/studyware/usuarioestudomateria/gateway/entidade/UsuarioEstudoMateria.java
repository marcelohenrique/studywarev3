package br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.ToString;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

@Entity
@Data
@ToString(exclude = { "usuarioEstudo" })
public class UsuarioEstudoMateria implements Entidade {

	private static final long serialVersionUID = 6291112509470918058L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioEstudoMateriaSeq")
	@SequenceGenerator(name = "usuarioEstudoMateriaSeq", sequenceName = "usuarioEstudoMateriaSeq", allocationSize = 1)
	private Long id;

	@JoinColumn(name = "usuarioEstudo", referencedColumnName = "id")
	@ManyToOne
	private UsuarioEstudo usuarioEstudo;

	@JoinColumn(name = "materia", referencedColumnName = "id")
	@ManyToOne
	private Materia materia;

	private Long tempoAlocado;

	private Long ordem;

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
		UsuarioEstudoMateria other = (UsuarioEstudoMateria) obj;
		if (this.materia == null) {
			if (other.materia != null) {
				return false;
			}
		} else if (!this.materia.equals(other.materia)) {
			return false;
		}
		if (this.usuarioEstudo == null) {
			if (other.usuarioEstudo != null) {
				return false;
			}
		} else if (!this.usuarioEstudo.equals(other.usuarioEstudo)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.materia == null) ? 0 : this.materia.hashCode());
		result = prime * result + ((this.usuarioEstudo == null) ? 0 : this.usuarioEstudo.hashCode());
		return result;
	}

}
