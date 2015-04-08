package br.com.guarasoft.studyware.estudodiario.gateway.converter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiarioEntidade;
import br.com.guarasoft.studyware.estudodiario.modelo.EstudoDiario;

public class UsuarioEstudoDiarioEntidadeConverter {

	private final DiaEntidadeConverter diaConverter = new DiaEntidadeConverter();

	public EstudoDiario convert(Estudo beanPai, EstudoDiarioEntidade entidade) {
		EstudoDiario bean = new EstudoDiario();

		bean.setId(entidade.getId());
		bean.setEstudo(beanPai);
		bean.setDia(this.diaConverter.convert(entidade.getDia()));
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));

		return bean;
	}

	public List<EstudoDiario> convert(Estudo beanPai, Set<EstudoDiarioEntidade> entidades) {
		List<EstudoDiario> beans = new ArrayList<>();

		for (EstudoDiarioEntidade entidade : entidades) {
			beans.add(this.convert(beanPai, entidade));
		}

		return beans;
	}

	private EstudoDiarioEntidade convert(EstudoEntidade entidadePai, EstudoDiario bean) {
		EstudoDiarioEntidade entidade = new EstudoDiarioEntidade();

		entidade.setId(bean.getId());
		entidade.setEstudo(entidadePai);
		entidade.setDia(this.diaConverter.convert(bean.getDia()));
		entidade.setTempoAlocado(bean.getTempoAlocado().getMillis());

		return entidade;
	}

	public Set<EstudoDiarioEntidade> convert(EstudoEntidade entidadePai, List<EstudoDiario> beans) {
		Set<EstudoDiarioEntidade> entidades = new LinkedHashSet<>();

		for (EstudoDiario bean : beans) {
			entidades.add(this.convert(entidadePai, bean));
		}

		return entidades;
	}

}
