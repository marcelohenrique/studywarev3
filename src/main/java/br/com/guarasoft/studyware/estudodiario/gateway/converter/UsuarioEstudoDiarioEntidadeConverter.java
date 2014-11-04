package br.com.guarasoft.studyware.estudodiario.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudodiario.bean.UsuarioEstudoDiarioBean;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.UsuarioEstudoDiario;
import br.com.guarasoft.studyware.estudodiario.gateway.entidade.UsuarioEstudoDiarioPK;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;

public class UsuarioEstudoDiarioEntidadeConverter {

	@Inject
	private DiaEntidadeConverter diaConverter;

	private UsuarioEstudoDiarioBean convert(UsuarioEstudoBean beanPai, UsuarioEstudoDiario entidade) {
		UsuarioEstudoDiarioBean bean = new UsuarioEstudoDiarioBean();

		bean.setUsuarioEstudo(beanPai);
		bean.setDia(this.diaConverter.convert(entidade.getPk().getDia()));
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));

		return bean;
	}

	public List<UsuarioEstudoDiarioBean> convert(UsuarioEstudoBean beanPai, List<UsuarioEstudoDiario> entidades) {
		List<UsuarioEstudoDiarioBean> beans = new ArrayList<>();

		for (UsuarioEstudoDiario entidade : entidades) {
			beans.add(this.convert(beanPai, entidade));
		}

		return beans;
	}

	private UsuarioEstudoDiario convert(UsuarioEstudo entidadePai, UsuarioEstudoDiarioBean bean) {
		UsuarioEstudoDiario entidade = new UsuarioEstudoDiario();

		UsuarioEstudoDiarioPK pk = new UsuarioEstudoDiarioPK();
		pk.setUsuarioEstudo(entidadePai);
		pk.setDia(this.diaConverter.convert(bean.getDia()));
		entidade.setPk(pk);
		entidade.setTempoAlocado(bean.getTempoAlocado().getMillis());

		return entidade;
	}

	public List<UsuarioEstudoDiario> convert(UsuarioEstudo entidadePai, List<UsuarioEstudoDiarioBean> beans) {
		List<UsuarioEstudoDiario> entidades = new ArrayList<>();

		for (UsuarioEstudoDiarioBean bean : beans) {
			entidades.add(this.convert(entidadePai, bean));
		}

		return entidades;
	}

}
