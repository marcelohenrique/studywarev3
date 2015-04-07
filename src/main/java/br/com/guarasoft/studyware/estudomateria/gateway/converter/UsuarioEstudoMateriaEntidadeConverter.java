package br.com.guarasoft.studyware.estudomateria.gateway.converter;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.EstudoMateriaEntidade;

public class UsuarioEstudoMateriaEntidadeConverter {

	public UsuarioEstudoMateriaBean convert(Estudo beanPai, EstudoMateriaEntidade entidade) {
		UsuarioEstudoMateriaBean bean = new UsuarioEstudoMateriaBean();

		bean.setId(entidade.getId());
		bean.setEstudo(beanPai);
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setOrdem(entidade.getOrdem());

		return bean;
	}

	public UsuarioEstudoMateriaBean convert(EstudoMateriaEntidade entidade) {
		UsuarioEstudoMateriaBean bean = new UsuarioEstudoMateriaBean();

		bean.setId(entidade.getId());
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setOrdem(entidade.getOrdem());

		return bean;
	}

	public EstudoMateriaEntidade convert(EstudoEntidade entidadePai, UsuarioEstudoMateriaBean bean) {
		EstudoMateriaEntidade entidade = new EstudoMateriaEntidade();

		entidade.setId(bean.getId());
		entidade.setEstudo(entidadePai);
		entidade.setTempoAlocado(bean.getTempoAlocado().getMillis());
		entidade.setOrdem(bean.getOrdem());

		return entidade;
	}

}
