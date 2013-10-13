/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concursomateria.entidade;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;
import br.com.guarasoft.concursos.infra.dao.Entidade;
import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;
import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateriaRepository;
import br.com.guarasoft.conteudoprogramatico.materia.persistence.Materia;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_CONCURSO_MATERIA")
@Data
@EqualsAndHashCode(callSuper = false)
public class ConcursoMateria extends AbstractDao<ConcursoMateria, Long>
		implements Entidade, ConcursoMateriaRepository {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6291112509470918058L;

	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_CONCURSO", referencedColumnName = "ID")
	private Concurso concurso;

	@OneToOne
	@JoinColumn(name = "ID_MATERIA", referencedColumnName = "ID")
	private Materia materia;

	@Column(name = "TEMPO_ALOCADO")
	private Long tempoAlocadoLong;

	public ConcursoMateria() {
		super(ConcursoMateria.class);
	}

	@Transient
	public Duration getTempoAlocado() {
		return new Duration(tempoAlocadoLong);
	}

	@Override
	public ConcursoMateria findById(Long id) {
		return find(id);
	}

	@Override
	public List<ConcursoMateria> findAll(Concurso concurso) {
		return entityManager
				.createQuery(
						"select cm from ConcursoMateria cm where cm.concurso.id = :idConcurso",
						ConcursoMateria.class)
				.setParameter("idConcurso", concurso.getId()).getResultList();
	}

	@Override
	public String toString() {
		return id.toString();
	}
}
