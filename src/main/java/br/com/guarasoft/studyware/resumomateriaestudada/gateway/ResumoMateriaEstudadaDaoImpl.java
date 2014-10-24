package br.com.guarasoft.studyware.resumomateriaestudada.gateway;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.guarasoft.studyware.resumomateriaestudada.bean.ResumoMateriaEstudadaBean;
import br.com.guarasoft.studyware.resumomateriaestudada.entidade.ResumoMateriaEstudada;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;

public class ResumoMateriaEstudadaDaoImpl implements ResumoMateriaEstudadaDao {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Override
	public List<ResumoMateriaEstudadaBean> findAll(UsuarioEstudoBean estudo) {
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

		Query query = this.entityManager.createNativeQuery(sql.toString(),
				ResumoMateriaEstudada.class);
		query.setParameter(1, estudo.getId());

		List<ResumoMateriaEstudada> resumo = query.getResultList();

		List<ResumoMateriaEstudadaBean> resumosBean = new ArrayList<>();

		return resumosBean;
	}

}