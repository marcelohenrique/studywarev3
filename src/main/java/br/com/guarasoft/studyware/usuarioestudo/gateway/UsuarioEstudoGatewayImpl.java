package br.com.guarasoft.studyware.usuarioestudo.gateway;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.converter.UsuarioEstudoBuilder;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

@Stateless
public class UsuarioEstudoGatewayImpl extends AbstractDao<UsuarioEstudo, Long> implements UsuarioEstudoGateway {

	private final UsuarioEstudoBuilder converter = new UsuarioEstudoBuilder();

	public UsuarioEstudoGatewayImpl() {
		super(UsuarioEstudo.class);
	}

	@Override
	public void cadastrar(UsuarioEstudoBean usuarioEstudoBean) {
		UsuarioEstudo usuarioEstudo = this.converter.convert(usuarioEstudoBean);

		this.entityManager.merge(usuarioEstudo);
	}

	@Override
	public List<UsuarioEstudoBean> recuperaEstudos(String email) {
		TypedQuery<UsuarioEstudo> typedQuery = this.entityManager.createQuery("from UsuarioEstudo eu where eu.email = :email", UsuarioEstudo.class);

		typedQuery.setParameter("email", email);

		List<UsuarioEstudo> estudosUsuario = typedQuery.getResultList();

		List<UsuarioEstudoBean> estudos = this.converter.converteMaterias().converteDias().convert(estudosUsuario);

		return estudos;
	}

	@Override
	public UsuarioEstudoBean buscaPorId(Long id) {
		UsuarioEstudo entidade = this.find(id);

		UsuarioEstudoBean bean = this.converter.converteMaterias().converteDias().convert(entidade);

		return bean;
	}

}
