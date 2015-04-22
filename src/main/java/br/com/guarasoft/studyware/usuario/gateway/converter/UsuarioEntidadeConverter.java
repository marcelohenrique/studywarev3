package br.com.guarasoft.studyware.usuario.gateway.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.guarasoft.studyware.usuario.gateway.entidade.UsuarioEntidade;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class UsuarioEntidadeConverter {

	public Usuario convert(UsuarioEntidade entidade) {
		Usuario bean = new Usuario();
		bean.setEmail(entidade.getEmail());
		bean.setAtivo(entidade.getAtivo());
		return bean;
	}

	public Collection<Usuario> convert(Collection<UsuarioEntidade> entidades) {
		List<Usuario> beans = new ArrayList<>();

		for (UsuarioEntidade entidade : entidades) {
			beans.add(this.convert(entidade));
		}

		return beans;
	}

	public UsuarioEntidade convert(Usuario bean) {
		UsuarioEntidade entidade = new UsuarioEntidade();
		entidade.setEmail(bean.getEmail());
		entidade.setAtivo(bean.isAtivo());
		return entidade;
	}

}
