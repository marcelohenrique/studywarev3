package br.com.guarasoft.studyware.estudo.gateway.converter;

import org.joda.time.DateTime;

import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;

public class EstudoEntidadeConverter {

	public Estudo convert(EstudoEntidade entidade) {
		Estudo bean = new Estudo(entidade.getNome(), null, new DateTime(
				entidade.getFim()));

		return bean;
	}

	public EstudoEntidade convert(Estudo bean) {
		EstudoEntidade entidade = new EstudoEntidade();

		entidade.setNome(bean.getNome());
		entidade.setFim(bean.getFim().toDate());

		return entidade;
	}

}
