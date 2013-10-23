/**
 * 
 */
package br.com.guarasoft.studyware.estudodiario.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.Dia;
import br.com.guarasoft.studyware.infra.dao.Entidade;

/**
 * @author guara
 * 
 */
@Entity
@Table( name = "TB_ESTUDO_DIA" )
@Data
public class EstudoDiario implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2509970879445306489L;

	@Id
	@Temporal(TemporalType.DATE)
	private Date dataInicioSemana;
	// private Long id;

	/*@OneToOne
	@JoinColumn(name = "ID_ESTUDO", referencedColumnName = "ID")
	private Estudo estudo;*/

	@Enumerated
	private Dia diaSemana;

	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private Long tempoEstudadoLong;

	@Transient
	public Duration getTempoEstudado() {
		return new Duration(tempoEstudadoLong);
	}

	@Column(name = "TEMPO_ALOCADO")
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private Long tempoAlocadoLong;

	@Transient
	public Duration getTempoAlocado() {
		return new Duration(tempoAlocadoLong);
	}

}
