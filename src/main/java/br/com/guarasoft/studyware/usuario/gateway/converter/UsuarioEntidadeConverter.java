package br.com.guarasoft.studyware.usuario.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.guarasoft.studyware.usuario.bean.UsuarioBean;
import br.com.guarasoft.studyware.usuario.gateway.entidade.Usuario;

public class UsuarioEntidadeConverter {

	public UsuarioBean convert(Usuario entidade) {
		UsuarioBean bean = new UsuarioBean();
		bean.setEmail(entidade.getEmail());
		bean.setAtivo(entidade.getAtivo());
		return bean;
	}

	public List<UsuarioBean> convert(List<Usuario> entidades) {
		List<UsuarioBean> beans = new ArrayList<>();

		for (Usuario entidade : entidades) {
			beans.add(this.convert(entidade));
		}

		return beans;
	}

	public Usuario convert(UsuarioBean bean) {
		Usuario entidade = new Usuario();
		entidade.setEmail(bean.getEmail());
		entidade.setAtivo(bean.isAtivo());
		return entidade;
	}

}
