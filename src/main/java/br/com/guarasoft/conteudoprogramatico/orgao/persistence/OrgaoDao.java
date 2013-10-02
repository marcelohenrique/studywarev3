/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.orgao.persistence;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;

/**
 * @author guara
 *
 */
public class OrgaoDao extends AbstractDao<Orgao, Long> implements OrgaoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4206937529377778455L;

	public OrgaoDao() {
		super( Orgao.class );
	}
}
