/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concurso.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import br.com.guarasoft.concursos.infra.dao.Entidade;
import br.com.guarasoft.conteudoprogramatico.banca.persistence.Banca;
import br.com.guarasoft.conteudoprogramatico.orgao.persistence.Orgao;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_CONCURSO")
@IdClass( ConcursoPK.class )
@Data
public class Concurso implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8551512558388108264L;

	@Id
	@OneToOne
	@JoinColumn(name = "ID_ORGAO")
	private Orgao orgao;

	@Id
	@OneToOne
	@JoinColumn(name = "ID_BANCA")
	private Banca banca;

	@Id
	private Short ano;
}
