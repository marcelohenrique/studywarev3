package br.com.guarasoft.studyware.materia.gateway;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
import br.com.guarasoft.studyware.materia.gateway.entidade.Materia;

@Stateless
public class MateriaGatewayImpl extends AbstractDao<Materia, Long> implements MateriaGateway {

	private final MateriaEntidadeConverter materiaEntidadeConverter = new MateriaEntidadeConverter();

	public MateriaGatewayImpl() {
		super(Materia.class);
	}

	@Override
	public List<MateriaBean> buscaMaterias() {
		TypedQuery<Materia> typedQuery = this.entityManager.createQuery("from Materia m order by m.sigla", Materia.class);

		List<Materia> entidades = typedQuery.getResultList();

		List<MateriaBean> beans = this.materiaEntidadeConverter.convert(entidades);

		return beans;
	}

	@Override
	public List<MateriaBean> buscaMateriasRestantes(UsuarioEstudoBean usuarioEstudoBean) {
		StringBuilder sql = new StringBuilder();
		sql.append("select m from Materia m ");
		sql.append(" where not exists ( ");
		sql.append("select 1 from UsuarioEstudoMateria uem ");
		sql.append("  join uem.materia mt ");
		sql.append(" where uem.usuarioEstudo.id = :usuarioEstudo ");
		sql.append("   and mt.id = m.id ) ");

		TypedQuery<Materia> typedQuery = this.entityManager.createQuery(sql.toString(), Materia.class);
		typedQuery.setParameter("usuarioEstudo", usuarioEstudoBean.getId());

		List<Materia> entidades = typedQuery.getResultList();

		List<MateriaBean> beans = this.materiaEntidadeConverter.convert(entidades);

		return beans;
	}

	@Override
	public void cadastrar(MateriaBean materiaBean) {
		Materia materia = this.materiaEntidadeConverter.convert(materiaBean);

		this.persist(materia);
	}

	@Override
	public void alterar(MateriaBean materiaBean) {
		Materia materia = this.materiaEntidadeConverter.convert(materiaBean);

		this.merge(materia);
	}

	@Override
	public void remover(MateriaBean materiaBean) {
		Materia materia = this.materiaEntidadeConverter.convert(materiaBean);

		this.remove(this.merge(materia));
	}

	@Override
	public MateriaBean buscaPorId(Long id) {
		Materia entidade = this.find(id);

		MateriaBean bean = this.materiaEntidadeConverter.convert(entidade);

		return bean;
	}

}
