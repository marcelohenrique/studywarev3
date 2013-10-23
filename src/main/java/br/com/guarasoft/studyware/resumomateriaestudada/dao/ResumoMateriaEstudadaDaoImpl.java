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
						"SELECT TEM.ID, SUM( THE.TEMPO_ESTUDADO ) SOMA_TEMPO, TM.NOME FROM TB_HISTORICO_ESTUDO THE RIGHT OUTER JOIN TB_ESTUDO_MATERIA TEM ON THE.ID_ESTUDO_MATERIA = TEM.ID JOIN TB_MATERIA TM ON TEM.ID_MATERIA = TM.ID WHERE TEM.ID_ESTUDO = ? GROUP BY TEM.ID, TM.NOME ORDER BY TEM.NR_ORDEM",
						ResumoMateriaEstudada.class).setParameter(1,
						estudo.getId());
		return query.getResultList();
	}

}