/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.orgao.persistence;

import br.com.guarasoft.concursos.infra.dao.Entidade;


/**
 * @author guara
 * 
 */
public interface OrgaoRepository extends Entidade {
	public Orgao find();
}
