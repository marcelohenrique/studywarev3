/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concurso.persistence;

import java.io.Serializable;

import lombok.Data;
import br.com.guarasoft.conteudoprogramatico.banca.persistence.Banca;
import br.com.guarasoft.conteudoprogramatico.orgao.persistence.Orgao;

/**
 * @author guara
 * 
 */
@Data
public class ConcursoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4847874129247339895L;

	private Orgao orgao;
	private Banca banca;
	private Short ano;

}
