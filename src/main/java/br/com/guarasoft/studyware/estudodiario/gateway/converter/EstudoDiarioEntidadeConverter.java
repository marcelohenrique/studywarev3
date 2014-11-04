package br.com.guarasoft.studyware.estudodiario.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudodiario.bean.EstudoDiarioBean;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiario;

public class EstudoDiarioEntidadeConverter {

	@Inject
	private DiaEntidadeConverter diaConverter;

	public List<EstudoDiarioBean> convert(List<EstudoDiario> entidades) {
		List<EstudoDiarioBean> beans = new ArrayList<>();

		EstudoDiarioBean bean = null;
		for (EstudoDiario entidade : entidades) {
			bean = convert(entidade);
			beans.add(bean);
		}

		return beans;
	}

	public EstudoDiarioBean convert(EstudoDiario entidade) {
		EstudoDiarioBean bean = new EstudoDiarioBean();

		bean.setInicioSemana(entidade.getInicioSemana());
		bean.setDiaSemana(this.diaConverter.convert(entidade.getDiaSemana()));
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setTempoEstudado(new Duration(entidade.getTempoEstudado()));

		return bean;
	}

}
