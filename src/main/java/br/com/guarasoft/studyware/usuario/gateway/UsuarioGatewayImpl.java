package br.com.guarasoft.studyware.usuario.gateway;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.usuario.gateway.converter.UsuarioEntidadeConverter;
import br.com.guarasoft.studyware.usuario.gateway.entidade.UsuarioEntidade;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@Stateless
public class UsuarioGatewayImpl extends AbstractDao<UsuarioEntidade, String>
implements UsuarioGateway {

	private final UsuarioEntidadeConverter usuarioEntidadeConverter = new UsuarioEntidadeConverter();

	public UsuarioGatewayImpl() {
		super(UsuarioEntidade.class);
	}

	@Override
	public Usuario pesquisaPorEmail(String email) {
		UsuarioEntidade usuario = this.find(email);

		if (usuario != null) {
			Usuario bean = this.usuarioEntidadeConverter.convert(usuario);
			return bean;
		}

		return null;
	}

	@Override
	public void cadastrar(Usuario usuario) {
		UsuarioEntidade entidade = this.usuarioEntidadeConverter.convert(usuario);
		this.persist(entidade);
	}

	@Override
	public Collection<Usuario> buscaUsuarios() {
		List<UsuarioEntidade> entidades = this.findAll("email");
		Collection<Usuario> beans = this.usuarioEntidadeConverter
				.convert(entidades);
		return beans;
	}

	@Override
	public void alterar(Usuario usuario) {
		UsuarioEntidade entidade = this.usuarioEntidadeConverter.convert(usuario);
		this.merge(entidade);
	}

	@Override
	public Collection<Usuario> buscaUsuarios(String email) {
		TypedQuery<UsuarioEntidade> typedQuery = this.entityManager
				.createQuery(
						"SELECT u FROM Usuario u WHERE UPPER( u.email ) LIKE UPPER( :email )",
						UsuarioEntidade.class);
		typedQuery.setParameter("email", "%" + email + "%");
		Collection<UsuarioEntidade> entidades = typedQuery.getResultList();

		return this.usuarioEntidadeConverter.convert(entidades);
	}

}
