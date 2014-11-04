package br.com.guarasoft.studyware.estudodiario.gateway.converter;

import br.com.guarasoft.studyware.estudodiario.bean.DiaBean;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.Dia;

public class DiaEntidadeConverter {

	public DiaBean convert(int diaSemana) {
		return DiaBean.values()[diaSemana];
	}

	public DiaBean convert(Dia entidade) {
		return this.convert(entidade.getId().intValue());
	}

	public Dia convert(DiaBean bean) {
		Dia entidade = new Dia();
		entidade.setId(new Integer(bean.ordinal()).longValue());
		return entidade;
	}

}
