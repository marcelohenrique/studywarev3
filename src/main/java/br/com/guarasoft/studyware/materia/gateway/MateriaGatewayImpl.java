package br.com.guarasoft.studyware.materia.gateway;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
import br.com.guarasoft.studyware.materia.gateway.entidade.MateriaEntidade;
import br.com.guarasoft.studyware.materia.modelo.Materia;

@Stateless
public class MateriaGatewayImpl extends AbstractDao<MateriaEntidade, Long> implements MateriaGateway {

	private final MateriaEntidadeConverter materiaEntidadeConverter = new MateriaEntidadeConverter();

	public MateriaGatewayImpl() {
		super(MateriaEntidade.class);
	}

	@Override
	public List<Materia> buscaMaterias() {
		TypedQuery<MateriaEntidade> typedQuery = this.entityManager
				.createQuery("from Materia m order by m.nome",
						MateriaEntidade.class);

		List<MateriaEntidade> entidades = typedQuery.getResultList();

		List<Materia> beans = this.materiaEntidadeConverter.convert(entidades);

		return beans;
	}

	@Override
	public List<Materia> buscaMateriasRestantes(Estudo estudo) {
		StringBuilder sql = new StringBuilder();
		sql.append("select m from Materia m ");
		sql.append(" where not exists ( ");
		sql.append("select 1 from EstudoMateriaEntidade uem ");
		sql.append("  join uem.materia mt ");
		sql.append(" where uem.estudo.nome = :nomeEstudo ");
		sql.append("   and mt.id = m.id ) ");

		TypedQuery<MateriaEntidade> typedQuery = this.entityManager.createQuery(sql.toString(), MateriaEntidade.class);
		typedQuery.setParameter("nomeEstudo", estudo.getNome());

		List<MateriaEntidade> entidades = typedQuery.getResultList();

		List<Materia> beans = this.materiaEntidadeConverter.convert(entidades);

		return beans;
	}

	@Override
	public void cadastrar(Materia materia) {
		MateriaEntidade materiaEntidade = this.materiaEntidadeConverter.convert(materia);

		this.persist(materiaEntidade);
	}

	@Override
	public void alterar(Materia materia) {
		MateriaEntidade materiaEntidade = this.materiaEntidadeConverter.convert(materia);

		this.merge(materiaEntidade);
	}

	@Override
	public void remover(Materia materia) {
		MateriaEntidade materiaEntidade = this.materiaEntidadeConverter.convert(materia);

		this.remove(this.merge(materiaEntidade));
	}

	@Override
	public Materia buscaPorId(Long id) {
		MateriaEntidade entidade = this.find(id);

		Materia bean = this.materiaEntidadeConverter.convert(entidade);

		return bean;
	}

}
