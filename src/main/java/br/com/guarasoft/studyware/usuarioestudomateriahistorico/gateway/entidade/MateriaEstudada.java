package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

@Entity
@Table(name = "TB_HISTORICO_ESTUDO")
@Data
@EqualsAndHashCode(callSuper = false)
public class MateriaEstudada implements Entidade {

	private static final long serialVersionUID = 4642707921273724986L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HISTORICO_ESTUDO")
	@SequenceGenerator(name = "SQ_HISTORICO_ESTUDO", sequenceName = "SQ_HISTORICO_ESTUDO", allocationSize = 1)
	@NotNull
	private Long id;

	@OneToOne
	@JoinColumn(name = "ID_ESTUDO_MATERIA")
	@NotNull
	private UsuarioEstudoMateria usuarioEstudoMateria;

	@Column(name = "DT_HORA_ESTUDO")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date dataHoraEstudo;

	/**
	 * Tempo estudado em segundos.
	 */
	@Column(name = "TEMPO_ESTUDADO")
	@NotNull
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private Long tempoEstudadoLong;

	@Column(name = "OBSERVACAO")
	@NotNull
	private String observacao;

}
