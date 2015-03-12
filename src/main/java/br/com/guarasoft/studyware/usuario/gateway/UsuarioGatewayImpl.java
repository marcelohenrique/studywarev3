package br.com.guarasoft.studyware.usuario.gateway;

import java.util.List;

import javax.ejb.Stateless;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.usuario.gateway.converter.UsuarioEntidadeConverter;
import br.com.guarasoft.studyware.usuario.gateway.entidade.UsuarioEntity;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

@Stateless
public class UsuarioGatewayImpl extends AbstractDao<UsuarioEntity, String> implements UsuarioGateway {

	private final UsuarioEntidadeConverter usuarioEntidadeConverter = new UsuarioEntidadeConverter();

	public UsuarioGatewayImpl() {
		super(UsuarioEntity.class);
	}

	@Override
	public Usuario pesquisaPorEmail(String email) {
		UsuarioEntity usuario = this.find(email);

		if (usuario != null) {
			Usuario bean = this.usuarioEntidadeConverter.convert(usuario);
			return bean;
		}

		return null;
	}

	@Override
	public void cadastrar(Usuario usuario) {
		UsuarioEntity entidade = this.usuarioEntidadeConverter.convert(usuario);
		this.persist(entidade);
	}

	@Override
	public List<Usuario> buscaUsuarios() {
		List<UsuarioEntity> entidades = this.findAll("email");
		List<Usuario> beans = this.usuarioEntidadeConverter.convert(entidades);
		return beans;
	}

	@Override
	public void alterar(Usuario usuario) {
		UsuarioEntity entidade = this.usuarioEntidadeConverter.convert(usuario);
		this.merge(entidade);
	}

}
