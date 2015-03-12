package br.com.guarasoft.studyware.usuario.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.guarasoft.studyware.usuario.gateway.entidade.UsuarioEntity;
import br.com.guarasoft.studyware.usuario.modelo.Usuario;

public class UsuarioEntidadeConverter {

	public Usuario convert(UsuarioEntity entidade) {
		Usuario bean = new Usuario();
		bean.setEmail(entidade.getEmail());
		bean.setAtivo(entidade.getAtivo());
		return bean;
	}

	public List<Usuario> convert(List<UsuarioEntity> entidades) {
		List<Usuario> beans = new ArrayList<>();

		for (UsuarioEntity entidade : entidades) {
			beans.add(this.convert(entidade));
		}

		return beans;
	}

	public UsuarioEntity convert(Usuario bean) {
		UsuarioEntity entidade = new UsuarioEntity();
		entidade.setEmail(bean.getEmail());
		entidade.setAtivo(bean.isAtivo());
		return entidade;
	}

}
