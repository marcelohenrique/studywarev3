package br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.UsuarioEstudoMateria;
import br.com.guarasoft.studyware.infra.dao.Entidade;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class UsuarioEstudoMateriaHistorico implements Entidade {

	private static final long serialVersionUID = 4642707921273724986L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HISTORICO_ESTUDO")
	@SequenceGenerator(name = "SQ_HISTORICO_ESTUDO", sequenceName = "SQ_HISTORICO_ESTUDO", allocationSize = 1)
	@NotNull
	private Long id;

	@JoinColumn(name = "usuarioEstudoMateria", referencedColumnName = "id")
	@ManyToOne
	@NotNull
	private UsuarioEstudoMateria usuarioEstudoMateria;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date horaEstudo;

	/**
	 * Tempo estudado em segundos.
	 */
	@NotNull
	private Long tempoEstudado;

	@NotNull
	private String observacao;

}
