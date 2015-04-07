package br.com.guarasoft.studyware.materia.gateway.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.ToString;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.UsuarioEstudoMateria;
import br.com.guarasoft.studyware.infra.dao.Entidade;

@Entity
@Data
@ToString(exclude = { "usuarioEstudoMaterias" })
public class Materia implements Entidade {

	private static final long serialVersionUID = -8089662973205060676L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "materiaSeq")
	@SequenceGenerator(name = "materiaSeq", sequenceName = "materiaSeq", allocationSize = 1)
	private Long id;
	private String sigla;
	private String nome;
	@OneToMany(mappedBy = "materia")
	private List<UsuarioEstudoMateria> usuarioEstudoMaterias;

}
