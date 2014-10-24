package br.com.guarasoft.studyware.usuarioestudo.gateway;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.converter.UsuarioEstudoEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

@Stateless
public class UsuarioEstudoGatewayImpl implements UsuarioEstudoGateway {

	@PersistenceContext(unitName = "studyware")
	private EntityManager entityManager;

	@Inject
	private UsuarioEstudoEntidadeConverter converter;

	@Override
	public void cadastrar(String email, String nomeEstudo, Date fim) {
		UsuarioEstudo usuarioEstudo = new UsuarioEstudo();
		usuarioEstudo.setEmail(email);
		usuarioEstudo.setNome(nomeEstudo);
		usuarioEstudo.setFim(fim);

		this.entityManager.persist(usuarioEstudo);
	}

	@Override
	public List<UsuarioEstudoBean> recuperaEstudos(String email) {
		TypedQuery<UsuarioEstudo> typedQuery = this.entityManager.createQuery(
				"from UsuarioEstudo eu where eu.email = :email",
				UsuarioEstudo.class);

		typedQuery.setParameter("email", email);

		List<UsuarioEstudo> estudosUsuario = typedQuery.getResultList();

		List<UsuarioEstudoBean> estudos = converter.convert(estudosUsuario);

		return estudos;
	}

}
