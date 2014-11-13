package br.com.guarasoft.studyware.estudodiario.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

@Entity
@Data
public class UsuarioEstudoDiario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UsuarioEstudoDiarioSeq")
	@SequenceGenerator(name = "UsuarioEstudoDiarioSeq", sequenceName = "UsuarioEstudoDiarioSeq", allocationSize = 1)
	private Long id;

	@JoinColumn(name = "usuarioEstudo", referencedColumnName = "id")
	@ManyToOne
	private UsuarioEstudo usuarioEstudo;

	@JoinColumn(name = "dia", referencedColumnName = "id")
	@ManyToOne
	private Dia dia;

	private Long tempoAlocado;

}
