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
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.EstudoMateriaHistoricoEntidade;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.materia.gateway.entidade.MateriaEntidade;

@Entity
@Table(name = "EstudoMateria")
@Data
@EqualsAndHashCode(of = { "estudo", "materia" })
@ToString(exclude = { "estudo" })
public class EstudoMateriaEntidade implements Entidade {

	private static final long serialVersionUID = 6291112509470918058L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioEstudoMateriaSeq")
	@SequenceGenerator(name = "usuarioEstudoMateriaSeq", sequenceName = "usuarioEstudoMateriaSeq", allocationSize = 1)
	private Long id;

	@JoinColumn(name = "estudo", referencedColumnName = "id")
	@ManyToOne
	private EstudoEntidade estudo;

	@JoinColumn(name = "materia", referencedColumnName = "id")
	@ManyToOne
	private MateriaEntidade materia;

	private Long tempoAlocado;

	private Long ordem;

	@OneToMany(mappedBy = "estudoMateria")
	private Set<EstudoMateriaHistoricoEntidade> historico;

}
