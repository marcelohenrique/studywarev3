package br.com.guarasoft.studyware.estudomateria.gateway.converter;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.EstudoMateriaEntidade;
import br.com.guarasoft.studyware.estudomateria.modelo.EstudoMateria;

public class UsuarioEstudoMateriaEntidadeConverter {

	public EstudoMateria convert(Estudo beanPai, EstudoMateriaEntidade entidade) {
		EstudoMateria bean = new EstudoMateria();

		bean.setId(entidade.getId());
		bean.setEstudo(beanPai);
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setOrdem(entidade.getOrdem());

		return bean;
	}

	public EstudoMateria convert(EstudoMateriaEntidade entidade) {
		EstudoMateria bean = new EstudoMateria();

		bean.setId(entidade.getId());
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setOrdem(entidade.getOrdem());

		return bean;
	}

	public EstudoMateriaEntidade convert(EstudoEntidade entidadePai, EstudoMateria bean) {
		EstudoMateriaEntidade entidade = new EstudoMateriaEntidade();

		entidade.setId(bean.getId());
		entidade.setEstudo(entidadePai);
		entidade.setTempoAlocado(bean.getTempoAlocado().getMillis());
		entidade.setOrdem(bean.getOrdem());

		return entidade;
	}

}
