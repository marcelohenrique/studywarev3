/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.orgao.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.guarasoft.concursos.infra.dao.Entidade;
import lombok.Data;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_ORGAO")
@Data
public class Orgao implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2770520443076495739L;

	@Id
	private Long id;
	private String sigla;
	private String nome;
	private String descricao;
	private String site;

}
