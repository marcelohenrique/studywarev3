package br.com.guarasoft.studyware.estudodiario.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudodiario.gateway.entidade.EstudoDiarioTransformer;
import br.com.guarasoft.studyware.estudodiario.modelo.EstudoSemanal;

public class EstudoDiarioEntidadeConverter {

	private final DiaEntidadeConverter diaConverter = new DiaEntidadeConverter();

	public List<EstudoSemanal> convert(List<EstudoDiarioTransformer> entidades) {
		List<EstudoSemanal> beans = new ArrayList<>();

		EstudoSemanal bean = null;
		for (EstudoDiarioTransformer entidade : entidades) {
			bean = this.convert(entidade);
			beans.add(bean);
		}

		return beans;
	}

	public EstudoSemanal convert(EstudoDiarioTransformer entidade) {
		EstudoSemanal bean = new EstudoSemanal();

		bean.setInicioSemana(entidade.getInicioSemana());
		bean.setDiaSemana(this.diaConverter.convert(entidade.getDiaSemana()));
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setTempoEstudado(new Duration(entidade.getTempoEstudado()));

		return bean;
	}

}
