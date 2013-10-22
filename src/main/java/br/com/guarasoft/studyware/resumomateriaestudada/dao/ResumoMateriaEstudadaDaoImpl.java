package br.com.guarasoft.studyware.resumomateriaestudada.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.guarasoft.studyware.estudo.entidade.Estudo;
import br.com.guarasoft.studyware.estudomateria.entidade.EstudoMateria;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.resumomateriaestudada.entidade.ResumoMateriaEstudada;

public class ResumoMateriaEstudadaDaoImpl extends
		AbstractDao<ResumoMateriaEstudada, EstudoMateria> implements
		ResumoMateriaEstudadaDao {

	public ResumoMateriaEstudadaDaoImpl() {
		super(ResumoMateriaEstudada.class);
	}

	/**
	 * @see br.com.guarasoft.studyware.estudomateria.dao.
	 *      EstudoMateriaDao#findAll(Estudo)
	 */
	@Override
	public List<ResumoMateriaEstudada> findAll(Estudo estudo) {
		Query query = entityManager
				.createNativeQuery(
						"SELECT TCM.ID, SUM( THE.TEMPO_ESTUDADO ) SOMA_TEMPO, TM.NOME FROM TB_HISTORICO_ESTUDO THE RIGHT OUTER JOIN TB_CONCURSO_MATERIA TCM ON THE.ID_CONCURSO_MATERIA = TCM.ID JOIN TB_MATERIA TM ON TCM.ID_MATERIA = TM.ID WHERE TCM.ID_CONCURSO = ? GROUP BY TCM.ID, TM.NOME ORDER BY TM.NOME",
						ResumoMateriaEstudada.class).setParameter(1,
						estudo.getId());
		return query.getResultList();
	}

}