package br.com.guarasoft.studyware.usuario.gateway;

import java.util.List;

import javax.ejb.Stateless;

import br.com.guarasoft.studyware.infra.dao.AbstractDao;
import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;
import br.com.guarasoft.studyware.usuario.gateway.converter.UsuarioEntidadeConverter;
import br.com.guarasoft.studyware.usuario.gateway.entidade.Usuario;

@Stateless
public class UsuarioGatewayImpl extends AbstractDao<Usuario, String> implements UsuarioGateway {

	private final UsuarioEntidadeConverter usuarioEntidadeConverter = new UsuarioEntidadeConverter();

	public UsuarioGatewayImpl() {
		super(Usuario.class);
	}

	@Override
	public UsuarioBean pesquisaPorEmail(String email) {
		Usuario usuario = this.find(email);

		if (usuario != null) {
			UsuarioBean bean = this.usuarioEntidadeConverter.convert(usuario);
			return bean;
		}

		return null;
	}

	@Override
	public void cadastrar(UsuarioBean usuario) {
		Usuario entidade = this.usuarioEntidadeConverter.convert(usuario);
		this.persist(entidade);
	}

	@Override
	public List<UsuarioBean> buscaUsuarios() {
		List<Usuario> entidades = this.findAll();
		List<UsuarioBean> beans = this.usuarioEntidadeConverter.convert(entidades);
		return beans;
	}

	@Override
	public void alterar(UsuarioBean usuario) {
		Usuario entidade = this.usuarioEntidadeConverter.convert(usuario);
		this.merge(entidade);
	}

}
