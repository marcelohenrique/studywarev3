/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concursomateriaestudada;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Query;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.Duration;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;
import br.com.guarasoft.concursos.infra.dao.Entidade;
import br.com.guarasoft.conteudoprogramatico.concurso.entidade.Concurso;
import br.com.guarasoft.conteudoprogramatico.concursomateria.entidade.ConcursoMateria;

/**
 * @author guara
 * 
 */
@Entity
@Table(name = "TB_HISTORICO_ESTUDO")
@Data
@EqualsAndHashCode(callSuper = false)
public class ConcursoMateriaEstudada extends
		AbstractDao<ConcursoMateriaEstudada, ConcursoMateria> implements
		Entidade, ConcursoMateriaEstudadaRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 312482194973348722L;

	@Id
	@OneToOne
	@JoinColumn(name = "ID_CONCURSO_MATERIA")
	private ConcursoMateria concursoMateria;

	@Column(name = "SOMA_TEMPO")
	private Long somaTempoLong;

	public Duration getSomaTempo() {
		if (somaTempoLong == null) {
			return new Duration(0);
		}
		return new Duration(somaTempoLong);
	}

	public ConcursoMateriaEstudada() {
		super(ConcursoMateriaEstudada.class);
	}

	/**
	 * @see br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.
	 *      ConcursoMateriaRepository#findAll(Concurso)
	 */
	@Override
	public List<ConcursoMateriaEstudada> findAll() {
		Query query = entityManager
				.createNativeQuery(
						"SELECT THE.ID_CONCURSO_MATERIA, SUM( THE.TEMPO_ESTUDADO ) SOMA_TEMPO, TM.NOME FROM TB_HISTORICO_ESTUDO THE JOIN TB_CONCURSO_MATERIA TCM ON THE.ID_CONCURSO_MATERIA = TCM.ID JOIN TB_MATERIA TM ON TCM.ID_MATERIA = TM.ID GROUP BY THE.ID_CONCURSO_MATERIA, TM.NOME ORDER BY TM.NOME",
						ConcursoMateriaEstudada.class);
		return query.getResultList();
	}

}
