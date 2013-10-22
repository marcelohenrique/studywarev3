/**
 * 
 */
package br.com.guarasoft.studyware.estudomateria.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.infra.dao.Entidade;
import br.com.guarasoft.studyware.materia.entidade.Materia;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_ESTUDO_MATERIA")
@Data
@EqualsAndHashCode(callSuper = false)
public class EstudoMateria implements Entidade {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6291112509470918058L;

	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_ESTUDO", referencedColumnName = "ID")
	private Estudo estudo;

	@OneToOne
	@JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID")
	private Materia materia;

	@Column(name = "TEMPO_ALOCADO")
	@Getter(AccessLevel.PRIVATE)
	@Setter(AccessLevel.PRIVATE)
	private Long tempoAlocadoLong;

	@Column(name = "NR_ORDEM")
	private Long nrOrdem;

	@Transient
	public Duration getTempoAlocado() {
		return new Duration(tempoAlocadoLong);
	}
}
