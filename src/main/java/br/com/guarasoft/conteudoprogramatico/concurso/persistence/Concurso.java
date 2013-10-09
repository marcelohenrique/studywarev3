/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concurso.persistence;

import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import br.com.guarasoft.concursos.infra.dao.AbstractDao;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_CONCURSO")
@Data
@EqualsAndHashCode(callSuper = false)
public class Concurso extends AbstractDao<ConcursoRepository, ConcursoPK>
		implements ConcursoRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8551512558388108264L;

	@EmbeddedId
	private ConcursoPK concursoPK;

	public Concurso() {
		super(ConcursoRepository.class);
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
}
