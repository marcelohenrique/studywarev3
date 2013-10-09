/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concursomateria.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.Entidade;
import br.com.guarasoft.concursos.infra.dao.Interval;
import br.com.guarasoft.conteudoprogramatico.concurso.persistence.ConcursoRepository;
import br.com.guarasoft.conteudoprogramatico.materia.persistence.Materia;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_CONCURSO_MATERIA")
@IdClass(ConcursoMateriaPK.class)
@TypeDef(name = "interval", typeClass = Interval.class)
@Data
public class ConcursoMateria implements Entidade {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6291112509470918058L;

	@Id
	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "ID_ORGAO", referencedColumnName = "ID_ORGAO"),
			@JoinColumn(name = "ID_BANCA", referencedColumnName = "ID_BANCA"),
			@JoinColumn(name = "ANO_CONCURSO", referencedColumnName = "ANO") })
	private ConcursoRepository concursoImpl;

	@Id
	@OneToOne
	@JoinColumn(name = "ID_MATERIA")
	private Materia materia;

	@Column(name = "TEMPO_ALOCADO")
	@Type(type = "interval")
	private Integer tempoAlocadoInt;

	/*@Column(name = "SOMA_TEMPO")
	@Type(type = "interval")
	private Integer somaTempoInt;*/

	@Transient
	public Duration getTempoAlocado() {
		return new Duration(tempoAlocadoInt * 1000);
	}

	/*@Transient
	public Duration getSomaTempo() {
		if (somaTempoInt == null) {
			return new Duration(0);
		}
		return new Duration(somaTempoInt * 1000);
	}*/
}
