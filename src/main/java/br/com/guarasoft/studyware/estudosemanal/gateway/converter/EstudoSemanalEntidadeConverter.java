package br.com.guarasoft.studyware.estudosemanal.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudosemanal.bean.EstudoSemanalBean;
import br.com.guarasoft.studyware.estudosemanal.gateway.entidade.EstudoSemanal;

public class EstudoSemanalEntidadeConverter {

	public List<EstudoSemanalBean> convert(List<EstudoSemanal> entidades) {
		List<EstudoSemanalBean> beans = new ArrayList<>();

		Duration estudoSemanalAcumuladoParcial = new Duration(0);
		EstudoSemanalBean bean = null;
		for (EstudoSemanal entidade : entidades) {
			bean = new EstudoSemanalBean();

			bean.setInicioSemana(entidade.getInicioSemana());
			bean.setTempoEstudado(new Duration(entidade.getTempoEstudado()));

			estudoSemanalAcumuladoParcial = estudoSemanalAcumuladoParcial.plus(entidade.getTempoEstudado());

			bean.setTempoEstudadoAcumulado(estudoSemanalAcumuladoParcial.toDuration());

			beans.add(bean);
		}

		return beans;
	}

}
