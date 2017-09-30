package br.com.guarasoft.studyware.banca.gateway.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.guarasoft.studyware.infra.dao.Entidade;
import lombok.Data;

@Entity(name = "Banca")
@Table(name = "Banca")
@Data
public class BancaEntidade implements Entidade {

	private static final long serialVersionUID = 7417713448631612490L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BancaSequence")
	@SequenceGenerator(name = "BancaSequence", sequenceName = "BancaSequence", allocationSize = 1)
	private Long id;
	@NotEmpty
	private String nome;
	@NotEmpty
	private String descricao;
	@NotEmpty
	private String site;

}
