/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materiaestudada.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;
import br.com.guarasoft.concursos.infra.dao.Entidade;
import br.com.guarasoft.conteudoprogramatico.concursomateria.entidade.ConcursoMateria;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_HISTORICO_ESTUDO")
@Data
@EqualsAndHashCode(callSuper = false)
public class MateriaEstudada extends AbstractDao<MateriaEstudada, Long>
		implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4642707921273724986L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HISTORICO_ESTUDO")
	@SequenceGenerator(name = "SQ_HISTORICO_ESTUDO", sequenceName = "SQ_HISTORICO_ESTUDO", allocationSize = 1)
	@NotNull
	private Long id;

	@OneToOne
	@JoinColumn(name = "ID_CONCURSO_MATERIA")
	@NotNull
	private ConcursoMateria concursoMateria;

	@Column(name = "DATA_HORA_ESTUDO")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date dataHoraEstudo;

	/**
	 * Tempo estudado em segundos.
	 */
	@Column(name = "TEMPO_ESTUDADO")
	@NotNull
	private Long tempoEstudadoLong;

	@Column(name = "OBSERVACAO")
	@NotNull
	private String observacao;

	public MateriaEstudada() {
		super(MateriaEstudada.class);
	}

	@Transient
	public Duration getTempoEstudado() {
		return new Duration(tempoEstudadoLong);
	}

	public void setTempoEstudado(Duration tempoEstudado) {
		tempoEstudadoLong = tempoEstudado.getStandardSeconds();
	}

	public List<MateriaEstudada> findAll() {
		return entityManager
				.createQuery(
						"select me from MateriaEstudada me order by me.dataHoraEstudo desc",
						MateriaEstudada.class).getResultList();
	}

	public MateriaEstudada saveOrUpdate() {
		return update(this);
	}

}
