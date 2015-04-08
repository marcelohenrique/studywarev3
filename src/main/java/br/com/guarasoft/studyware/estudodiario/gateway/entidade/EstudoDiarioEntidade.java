package br.com.guarasoft.studyware.estudodiario.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;

@Entity
@Table(name = "EstudoDiario")
@Data
public class EstudoDiarioEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsuarioEstudoDiarioSeq")
	@SequenceGenerator(name = "UsuarioEstudoDiarioSeq", sequenceName = "UsuarioEstudoDiarioSeq", allocationSize = 1)
	private Long id;

	@JoinColumn(name = "estudo", referencedColumnName = "id")
	@ManyToOne
	private EstudoEntidade estudo;

	@JoinColumn(name = "dia", referencedColumnName = "id")
	@ManyToOne
	private DiaEntidade dia;

	private Long tempoAlocado;

}
