package br.com.guarasoft.studyware.banca.gateway;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.banca.gateway.converter.BancaBuilder;
import br.com.guarasoft.studyware.banca.gateway.entidade.BancaEntidade;
import br.com.guarasoft.studyware.banca.modelo.Banca;

@Stateless
public class BancaGatewayImpl implements BancaGateway {

	private static final long serialVersionUID = -135577931313630601L;

	@PersistenceContext
	private EntityManager em;

	private @Inject BancaBuilder builder;

	@Override
	public Collection<Banca> consulta() {
		TypedQuery<BancaEntidade> query = this.em.createQuery("SELECT b FROM Banca b", BancaEntidade.class);
		Collection<BancaEntidade> entidades = query.getResultList();
		Collection<Banca> beans = this.builder.convert(entidades);
		return beans;
	}

	@Override
	public void cadastra(Banca banca) {
		BancaEntidade entidade = this.builder.convert(banca);
		this.em.persist(entidade);
	}

}
