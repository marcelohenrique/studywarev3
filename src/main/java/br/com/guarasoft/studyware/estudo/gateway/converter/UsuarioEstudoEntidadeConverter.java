package br.com.guarasoft.studyware.estudo.gateway.converter;

import br.com.guarasoft.studyware.estudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.estudo.gateway.entidade.UsuarioEstudo;

public class UsuarioEstudoEntidadeConverter {

	public UsuarioEstudoBean convert(UsuarioEstudo entidade) {
		UsuarioEstudoBean bean = new UsuarioEstudoBean();

		bean.setId(entidade.getId());
		bean.setNome(entidade.getNome());
		bean.setFim(entidade.getFim());

		return bean;
	}

	public UsuarioEstudo convert(UsuarioEstudoBean bean) {
		UsuarioEstudo entidade = new UsuarioEstudo();

		entidade.setId(bean.getId());
		entidade.setNome(bean.getNome());
		entidade.setFim(bean.getFim());

		return entidade;
	}

}
