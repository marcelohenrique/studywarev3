package br.com.guarasoft.studyware.usuarioestudo.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

public class UsuarioEstudoEntidadeConverter {

	public UsuarioEstudoBean convert(UsuarioEstudo entidade) {
		UsuarioEstudoBean bean = new UsuarioEstudoBean();

		bean.setId(entidade.getId());
		bean.setEmail(entidade.getEmail());
		bean.setNome(entidade.getNome());
		bean.setFim(entidade.getFim());

		return bean;
	}

	public UsuarioEstudo convert(UsuarioEstudoBean bean) {
		UsuarioEstudo entidade = new UsuarioEstudo();

		entidade.setId(bean.getId());
		entidade.setEmail(bean.getEmail());
		entidade.setNome(bean.getNome());
		entidade.setFim(bean.getFim());

		return entidade;
	}

	public List<UsuarioEstudoBean> convert(List<UsuarioEstudo> entidades) {
		List<UsuarioEstudoBean> beans = new ArrayList<>();

		UsuarioEstudoBean bean = null;
		for (UsuarioEstudo usuarioEstudo : entidades) {
			bean = convert(usuarioEstudo);

			beans.add(bean);
		}

		return beans;
	}

}
