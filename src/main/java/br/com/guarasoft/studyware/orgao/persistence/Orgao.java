/**
 * 
 */
package br.com.guarasoft.studyware.orgao.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_ORGAO")
@Data
@EqualsAndHashCode(callSuper = false)
public class Orgao extends AbstractDao<Orgao, Long> implements OrgaoRepository {

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

	public Orgao() {
		super(Orgao.class);
	}

	@Override
	public Orgao find() {
		return super.find(id.longValue());
	}

}
