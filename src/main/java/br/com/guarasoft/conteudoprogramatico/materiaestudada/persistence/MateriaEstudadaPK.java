/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateria;

/**
 * @author guara
 * 
 */
@Embeddable
@Data
public class MateriaEstudadaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4952251350816601208L;

	@OneToOne
	@JoinColumns({
			@JoinColumn(name = "ID_ORGAO", referencedColumnName = "ID_ORGAO"),
			@JoinColumn(name = "ID_BANCA", referencedColumnName = "ID_BANCA"),
			@JoinColumn(name = "ANO_CONCURSO", referencedColumnName = "ANO_CONCURSO"),
			@JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID_MATERIA") })
	private ConcursoMateria concursoMateria;

	@Column(name = "DATA_HORA_ESTUDO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraEstudo;

}
