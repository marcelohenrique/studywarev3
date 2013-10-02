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

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.Entidade;
import br.com.guarasoft.concursos.infra.dao.Interval;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_HISTORICO_ESTUDO")
@TypeDef(name = "interval", typeClass = Interval.class)
@Data
public class EstudoSemanal implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6647557331665653590L;

	@Id
	@Temporal(TemporalType.DATE)
	private Date dataInicioSemana;
	@Type(type = "interval")
	private Integer tempoEstudadoInt;
	@Transient
	private Duration tempoEstudadoAcumulado;

	public Date getDataFimSemana() {
		return new DateTime(dataInicioSemana).plusDays(6).toDate();
	}

	public Duration getTempoEstudado() {
		return new Duration(tempoEstudadoInt * 1000);
	}

}
