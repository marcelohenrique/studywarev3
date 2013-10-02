/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.Entidade;
import br.com.guarasoft.concursos.infra.dao.Interval;
import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateria;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_HISTORICO_ESTUDO")
@IdClass(MateriaEstudadaPK.class)
@TypeDef(name = "interval", typeClass = Interval.class)
@Data
public class MateriaEstudada implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4642707921273724986L;

	@Id
	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "ID_ORGAO", referencedColumnName = "ID_ORGAO"),
			@JoinColumn(name = "ID_BANCA", referencedColumnName = "ID_BANCA"),
			@JoinColumn(name = "ANO_CONCURSO", referencedColumnName = "ANO_CONCURSO"),
			@JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID_MATERIA") })
	private ConcursoMateria concursoMateria;

	@Id
	@Column(name = "DATA_HORA_ESTUDO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraEstudo;

	@Column(name = "TEMPO_ESTUDADO")
	@Type(type = "interval")
	private Integer tempoEstudadoInt;

	@Column(name = "OBSERVACAO")
	private String observacao;

	@Transient
	public Duration getTempoEstudado() {
		return new Duration(tempoEstudadoInt * 1000);
	}

	public void setTempoEstudado(Duration tempoEstudado) {
	}

}
