/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.estudosemanal.persistence;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.Entidade;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_HISTORICO_ESTUDO")
@Data
public class EstudoSemanal implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6647557331665653590L;

	@Id
	@Temporal(TemporalType.DATE)
	private Date dataInicioSemana;
	private Long tempoEstudadoLong;
	@Transient
	private Duration tempoEstudadoAcumulado;

	public Date getDataFimSemana() {
		return new DateTime(dataInicioSemana).plusDays(6).toDate();
	}

	public Duration getTempoEstudado() {
		return new Duration(tempoEstudadoLong);
	}

}
