/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concursomateria.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.guarasoft.concursos.infra.dao.AbstractDao;

/**
 * @author guara
 * 
 */
public class ConcursoMateriaDao extends AbstractDao<ConcursoMateria, Long>
		implements ConcursoMateriaRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4417880623996892004L;

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	public ConcursoMateriaDao() {
		super(ConcursoMateria.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.
	 * ConcursoMateriaRepository#findAll()
	 */
	@Override
	public List<ConcursoMateriaEstudada> findAll() {
		// TypedQuery<ConcursoMateria> typedQuery =
		// entityManager.createQuery("select cm from ConcursoMateria cm",
		// ConcursoMateria.class);
		Query query = entityManager.createNativeQuery("SELECT TCM.ID_ORGAO, "
				+ "TCM.ID_BANCA, " + "TCM.ANO_CONCURSO, " + "TCM.ID_MATERIA, "
				+ "TCM.TEMPO_ALOCADO, " + "THE.SOMA_TEMPO "
				+ "FROM TB_CONCURSO_MATERIA TCM " + "LEFT OUTER JOIN ( "
				+ "SELECT ID_MATERIA, " + "SUM( TEMPO_ESTUDADO ) SOMA_TEMPO "
				+ "FROM TB_HISTORICO_ESTUDO " + "GROUP BY ID_MATERIA ) THE "
				+ "ON TCM.ID_MATERIA = THE.ID_MATERIA",
				ConcursoMateriaEstudada.class);
		/*
		 * TypedQuery<ConcursoMateriaEstudada> query =
		 * entityManager.createQuery(
		 * "SELECT NEW br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateriaEstudada(cm, the.somaTempo) "
		 * + "FROM ConcursoMateria cm " + "LEFT OUTER JOIN ( " +
		 * "SELECT me.concursoMateria.materia.codigo idMateria, " +
		 * "SUM( me.tempoEstudadoInt ) somaTempo " + "FROM MateriaEstudadaImpl me "
		 * + "GROUP BY me.concursoMateria.materia.codigo ) the " +
		 * "ON cm.materia.codigo = the.idMateria",
		 * ConcursoMateriaEstudada.class);
		 */
		return query.getResultList();
	}

}
