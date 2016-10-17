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
		UsuarioEntidade usuarioEntidade = this.find(email);

		if (usuarioEntidade != null) {
			Usuario usuario = this.usuarioEntidadeConverter.convert(usuarioEntidade);
			return usuario;
		}

		return null;
	}

	@Override
	public void cadastrar(Usuario usuario) {
		UsuarioEntidade usuarioEntidade = this.usuarioEntidadeConverter.convert(usuario);
		this.persist(usuarioEntidade);
	}

	@Override
	public Collection<Usuario> buscaUsuarios() {
		List<UsuarioEntidade> usuarioEntidades = this.findAll("email");
		Collection<Usuario> usuarios = this.usuarioEntidadeConverter
				.convert(usuarioEntidades);
		return usuarios;
	}

	@Override
	public void alterar(Usuario usuario) {
		UsuarioEntidade usuarioEntidade = this.usuarioEntidadeConverter.convert(usuario);
		this.merge(usuarioEntidade);
	}

	@Override
	public Collection<Usuario> buscaUsuarios(String email) {
		TypedQuery<UsuarioEntidade> typedQuery = this.entityManager
				.createQuery(
						"SELECT u FROM Usuario u WHERE UPPER( u.email ) LIKE UPPER( :email )",
						UsuarioEntidade.class);
		typedQuery.setParameter("email", "%" + email + "%");
		Collection<UsuarioEntidade> usuarioEntidades = typedQuery.getResultList();

		return this.usuarioEntidadeConverter.convert(usuarioEntidades);
	}

}
