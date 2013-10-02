/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concursomateria.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Type;
import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.Entidade;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_HISTORICO_ESTUDO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcursoMateriaEstudada implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 312482194973348722L;

	@Id
	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "ID_ORGAO", referencedColumnName = "ID_ORGAO"),
			@JoinColumn(name = "ID_BANCA", referencedColumnName = "ID_BANCA"),
			@JoinColumn(name = "ANO_CONCURSO", referencedColumnName = "ANO_CONCURSO"),
			@JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID_MATERIA") })
	private ConcursoMateria concursoMateria;

	@Column(name = "SOMA_TEMPO")
	@Type(type = "interval")
	private Integer somaTempoInt;

	public Duration getSomaTempo() {
		if (somaTempoInt == null) {
			return new Duration(0);
		}
		return new Duration(somaTempoInt * 1000);
	}

}
