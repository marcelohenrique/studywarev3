/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.materiaestudada.persistence;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateria;

/**
 * @author guara
 * 
 */
@Data
public class MateriaEstudadaPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4952251350816601208L;

	private ConcursoMateria concursoMateria;
	private Date dataHoraEstudo;

}
