/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.banca.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import br.com.guarasoft.concursos.infra.dao.Entidade;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_BANCA")
@Data
public class Banca implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6783677496132153172L;

	@Id
	private Long id;
	private String sigla;
	private String nome;
	private String descricao;
	private String site;

}
