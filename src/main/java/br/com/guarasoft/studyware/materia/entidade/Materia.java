/**
 * 
 */
package br.com.guarasoft.studyware.materia.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import br.com.guarasoft.studyware.infra.dao.Entidade;

/**
 * @author guara
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_MATERIA")
public class Materia implements Entidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5004814730639406766L;

	@Id
	@Column(name = "ID")
	private Long codigo;
	private String sigla;
	private String nome;
	private String descricao;

}
