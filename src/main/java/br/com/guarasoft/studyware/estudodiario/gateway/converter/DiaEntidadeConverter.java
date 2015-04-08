package br.com.guarasoft.studyware.estudodiario.gateway.converter;

import br.com.guarasoft.studyware.estudodiario.gateway.entidade.DiaEntidade;
import br.com.guarasoft.studyware.estudodiario.modelo.Dia;

public class DiaEntidadeConverter {

	public Dia convert(int diaSemana) {
		return Dia.values()[diaSemana];
	}

	public Dia convert(DiaEntidade entidade) {
		return this.convert(entidade.getId().intValue());
	}

	public DiaEntidade convert(Dia bean) {
		DiaEntidade entidade = new DiaEntidade();
		entidade.setId(new Integer(bean.ordinal()).longValue());
		return entidade;
	}

}
