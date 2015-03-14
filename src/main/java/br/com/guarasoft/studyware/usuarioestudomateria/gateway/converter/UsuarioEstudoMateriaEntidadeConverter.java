package br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.estudo.gateway.entidade.UsuarioEstudo;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

public class UsuarioEstudoMateriaEntidadeConverter {

	public UsuarioEstudoMateriaBean convert(UsuarioEstudoBean beanPai, UsuarioEstudoMateria entidade) {
		UsuarioEstudoMateriaBean bean = new UsuarioEstudoMateriaBean();

		bean.setId(entidade.getId());
		bean.setUsuarioEstudo(beanPai);
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setOrdem(entidade.getOrdem());

		return bean;
	}

	public UsuarioEstudoMateriaBean convert(UsuarioEstudoMateria entidade) {
		UsuarioEstudoMateriaBean bean = new UsuarioEstudoMateriaBean();

		bean.setId(entidade.getId());
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setOrdem(entidade.getOrdem());

		return bean;
	}

	public UsuarioEstudoMateria convert(UsuarioEstudo entidadePai, UsuarioEstudoMateriaBean bean) {
		UsuarioEstudoMateria entidade = new UsuarioEstudoMateria();

		entidade.setId(bean.getId());
		entidade.setUsuarioEstudo(entidadePai);
		entidade.setTempoAlocado(bean.getTempoAlocado().getMillis());
		entidade.setOrdem(bean.getOrdem());

		return entidade;
	}

}
