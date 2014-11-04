package br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

@Entity
@Data
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

}
