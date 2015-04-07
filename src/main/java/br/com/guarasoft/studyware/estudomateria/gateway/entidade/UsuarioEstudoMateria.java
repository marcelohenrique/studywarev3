package br.com.guarasoft.studyware.estudomateria.gateway.entidade;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.ToString;
import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.UsuarioEstudoMateriaHistorico;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;

@Entity
@Data
@ToString(exclude = { "estudo" })
public class UsuarioEstudoMateria implements Entidade {

	private static final long serialVersionUID = 6291112509470918058L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioEstudoMateriaSeq")
	@SequenceGenerator(name = "usuarioEstudoMateriaSeq", sequenceName = "usuarioEstudoMateriaSeq", allocationSize = 1)
	private Long id;

	@JoinColumn(name = "usuarioEstudo", referencedColumnName = "id")
	@ManyToOne
	private EstudoEntidade estudo;

	@JoinColumn(name = "materia", referencedColumnName = "id")
	@ManyToOne
	private Materia materia;

	private Long tempoAlocado;

	private Long ordem;

	@OneToMany(mappedBy = "usuarioEstudoMateria")
	private Set<UsuarioEstudoMateriaHistorico> historico;

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
		if (this.estudo == null) {
			if (other.estudo != null) {
				return false;
			}
		} else if (!this.estudo.equals(other.estudo)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.materia == null) ? 0 : this.materia.hashCode());
		result = prime * result + ((this.estudo == null) ? 0 : this.estudo.hashCode());
		return result;
	}

}
