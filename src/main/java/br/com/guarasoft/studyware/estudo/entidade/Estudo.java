/**
 * 
 */
package br.com.guarasoft.studyware.estudo.entidade;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import br.com.guarasoft.studyware.infra.dao.Entidade;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_ESTUDO")
@Data
public class Estudo implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2577878889707869478L;

	@Id
	private Long id;
	private String nome;
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_FIM")
	private Date dataFim;

}
