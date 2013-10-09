/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concurso.persistence;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import br.com.guarasoft.conteudoprogramatico.banca.persistence.Banca;
import br.com.guarasoft.conteudoprogramatico.orgao.persistence.Orgao;

/**
 * @author guara
 * 
 */
@Embeddable
@Data
public class ConcursoPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4847874129247339895L;

	@OneToOne
	@JoinColumn(name = "ID_ORGAO")
	private Orgao orgao;

	@OneToOne
	@JoinColumn(name = "ID_BANCA")
	private Banca banca;

	private Short ano;

}
