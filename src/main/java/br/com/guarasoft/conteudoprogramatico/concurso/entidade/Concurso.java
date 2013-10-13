/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concurso.entidade;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.guarasoft.concursos.infra.dao.AbstractDao;
import br.com.guarasoft.conteudoprogramatico.banca.persistence.Banca;
import br.com.guarasoft.conteudoprogramatico.concurso.persistence.ConcursoRepository;
import br.com.guarasoft.conteudoprogramatico.orgao.persistence.Orgao;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_CONCURSO")
@Data
@EqualsAndHashCode(callSuper = false)
public class Concurso extends AbstractDao<Concurso, Long> implements
		ConcursoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8551512558388108264L;

	@Id
	private Long id;

	@OneToOne
	@JoinColumn(name = "ID_ORGAO", referencedColumnName = "ID")
	private Orgao orgao;

	@OneToOne
	@JoinColumn(name = "ID_BANCA", referencedColumnName = "ID")
	private Banca banca;

	private Short ano;

	private Short sequencial;

	private String site;

	public Concurso() {
		super(Concurso.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.guarasoft.conteudoprogramatico.concurso.persistence.ConcursoRepository
	 * #findAll ()
	 */
	@Override
	public List<Concurso> findAll() {
		return entityManager.createQuery("select me from Concurso me",
				Concurso.class).getResultList();
	}

	@Override
	public Concurso findById(Long id) {
		return find(id);
	}

	@Override
	public String toString() {
		return id.toString();
	}

}
