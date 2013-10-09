/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concursomateria.persistence;

import java.io.Serializable;

import lombok.Data;
import br.com.guarasoft.conteudoprogramatico.concurso.persistence.ConcursoRepository;
import br.com.guarasoft.conteudoprogramatico.materia.persistence.Materia;

/**
 * @author guara
 * 
 */
@Data
public class ConcursoMateriaPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6909243553695084783L;
	private ConcursoRepository concursoImpl;
	private Materia materia;
}
