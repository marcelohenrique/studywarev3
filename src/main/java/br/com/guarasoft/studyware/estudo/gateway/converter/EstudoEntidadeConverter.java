package br.com.guarasoft.studyware.estudo.gateway.converter;

import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;

public class EstudoEntidadeConverter {

	public Estudo convert(EstudoEntidade entidade) {
		Estudo bean = new Estudo(entidade.getId(), entidade.getNome(),
				entidade.getFim());

		return bean;
	}

	public EstudoEntidade convert(Estudo bean) {
		EstudoEntidade entidade = new EstudoEntidade();

		entidade.setId(bean.getId());
		entidade.setNome(bean.getNome());
		entidade.setFim(bean.getFim());

		return entidade;
	}

}
