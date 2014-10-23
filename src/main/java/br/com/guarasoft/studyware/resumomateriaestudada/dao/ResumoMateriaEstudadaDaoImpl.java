package br.com.guarasoft.studyware.resumomateriaestudada.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.guarasoft.studyware.estudomateria.entidade.EstudoMateria;
import br.com.guarasoft.studyware.estudousuario.bean.EstudoUsuarioBean;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.resumomateriaestudada.entidade.ResumoMateriaEstudada;

public class ResumoMateriaEstudadaDaoImpl extends
		AbstractDao<ResumoMateriaEstudada, EstudoMateria> implements
		ResumoMateriaEstudadaDao {

	public ResumoMateriaEstudadaDaoImpl() {
		super(ResumoMateriaEstudada.class);
	}

	@Override
	public List<ResumoMateriaEstudada> findAll(EstudoUsuarioBean estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT TEM.ID, ");
		sql.append("       SUM( THE.TEMPO_ESTUDADO ) SOMA_TEMPO, ");
		sql.append("       TM.NOME ");
		sql.append("  FROM TB_HISTORICO_ESTUDO THE ");
		sql.append(" RIGHT OUTER JOIN TB_ESTUDO_MATERIA TEM ");
		sql.append("    ON THE.ID_ESTUDO_MATERIA = TEM.ID ");
		sql.append("  JOIN Materia TM ON TEM.ID_MATERIA = TM.ID ");
		sql.append(" WHERE TEM.ID_ESTUDO = ? ");
		sql.append(" GROUP BY TEM.ID, TM.NOME ");
		sql.append(" ORDER BY TEM.NR_ORDEM ");

		Query query = entityManager.createNativeQuery(sql.toString(),
				ResumoMateriaEstudada.class).setParameter(1, estudo.getId());
		return query.getResultList();
	}

}