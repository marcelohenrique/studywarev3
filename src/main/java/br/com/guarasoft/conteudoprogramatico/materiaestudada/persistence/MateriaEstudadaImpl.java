/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;
import br.com.guarasoft.concursos.infra.dao.Interval;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_HISTORICO_ESTUDO")
@TypeDef(name = "interval", typeClass = Interval.class)
@Data
@EqualsAndHashCode(callSuper = false)
public class MateriaEstudadaImpl extends
		AbstractDao<MateriaEstudada, MateriaEstudadaPK> implements
		MateriaEstudada {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4642707921273724986L;

	@EmbeddedId
	private MateriaEstudadaPK materiaEstudadaPK;

	/**
	 * Tempo estudado em segundos.
	 */
	@Column(name = "TEMPO_ESTUDADO")
	@Type(type = "interval")
	private Integer tempoEstudadoInt;

	@Column(name = "OBSERVACAO")
	private String observacao;

	public MateriaEstudadaImpl() {
		super(MateriaEstudada.class);
	}

	@Override
	@Transient
	public Duration getTempoEstudado() {
		return new Duration(tempoEstudadoInt * 1000);
	}

	@Override
	public void setTempoEstudado(Duration tempoEstudado) {
		tempoEstudadoInt = (int) tempoEstudado.getStandardSeconds();
	}

	@Override
	public List<MateriaEstudada> findAll() {
		return entityManager
				.createQuery(
						"select me from MateriaEstudadaImpl me order by me.dataHoraEstudo desc")
				.getResultList();
	}

	@Override
	public void saveOrUpdate() {
		update(this);
	}

}
