package br.com.guarasoft.studyware.estudodiario.gateway.converter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudodiario.bean.UsuarioEstudoDiarioBean;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.UsuarioEstudoDiario;

public class UsuarioEstudoDiarioEntidadeConverter {

	private final DiaEntidadeConverter diaConverter = new DiaEntidadeConverter();

	private UsuarioEstudoDiarioBean convert(Estudo beanPai, UsuarioEstudoDiario entidade) {
		UsuarioEstudoDiarioBean bean = new UsuarioEstudoDiarioBean();

		bean.setId(entidade.getId());
		bean.setUsuarioEstudo(beanPai);
		bean.setDia(this.diaConverter.convert(entidade.getDia()));
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));

		return bean;
	}

	public List<UsuarioEstudoDiarioBean> convert(Estudo beanPai, Set<UsuarioEstudoDiario> entidades) {
		List<UsuarioEstudoDiarioBean> beans = new ArrayList<>();

		for (UsuarioEstudoDiario entidade : entidades) {
			beans.add(this.convert(beanPai, entidade));
		}

		return beans;
	}

	private UsuarioEstudoDiario convert(EstudoEntidade entidadePai, UsuarioEstudoDiarioBean bean) {
		UsuarioEstudoDiario entidade = new UsuarioEstudoDiario();

		entidade.setId(bean.getId());
		entidade.setEstudo(entidadePai);
		entidade.setDia(this.diaConverter.convert(bean.getDia()));
		entidade.setTempoAlocado(bean.getTempoAlocado().getMillis());

		return entidade;
	}

	public Set<UsuarioEstudoDiario> convert(EstudoEntidade entidadePai, List<UsuarioEstudoDiarioBean> beans) {
		Set<UsuarioEstudoDiario> entidades = new LinkedHashSet<>();

		for (UsuarioEstudoDiarioBean bean : beans) {
			entidades.add(this.convert(entidadePai, bean));
		}

		return entidades;
	}

}
