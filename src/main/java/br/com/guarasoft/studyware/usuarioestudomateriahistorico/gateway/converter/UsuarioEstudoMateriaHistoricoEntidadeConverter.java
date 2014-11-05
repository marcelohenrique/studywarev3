package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter.UsuarioEstudoMateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.UsuarioEstudoMateriaHistorico;

public class UsuarioEstudoMateriaHistoricoEntidadeConverter {

	private final UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter = new UsuarioEstudoMateriaEntidadeConverter();

	public UsuarioEstudoMateriaHistorico convert(UsuarioEstudoMateriaHistoricoBean bean) {
		UsuarioEstudoMateriaHistorico entidade = new UsuarioEstudoMateriaHistorico();

		entidade.setId(bean.getId());
		entidade.setUsuarioEstudoMateria(this.usuarioEstudoMateriaEntidadeConverter.convert(bean.getUsuarioEstudoMateria()));
		entidade.setHoraEstudo(bean.getHoraEstudo());
		entidade.setTempoEstudado(bean.getTempoEstudado().getMillis());
		entidade.setObservacao(bean.getObservacao());

		return entidade;
	}

	public UsuarioEstudoMateriaHistoricoBean convert(UsuarioEstudoBean beanPai, UsuarioEstudoMateriaHistorico entidade) {
		UsuarioEstudoMateriaHistoricoBean bean = new UsuarioEstudoMateriaHistoricoBean();

		bean.setId(entidade.getId());
		bean.setUsuarioEstudoMateria(this.usuarioEstudoMateriaEntidadeConverter.convert(beanPai, entidade.getUsuarioEstudoMateria()));
		bean.setHoraEstudo(entidade.getHoraEstudo());
		bean.setTempoEstudado(new Duration(entidade.getTempoEstudado()));
		bean.setObservacao(entidade.getObservacao());

		return bean;
	}

	public List<UsuarioEstudoMateriaHistoricoBean> convert(UsuarioEstudoBean beanPai, List<UsuarioEstudoMateriaHistorico> entidades) {
		List<UsuarioEstudoMateriaHistoricoBean> beans = new ArrayList<>();

		for (UsuarioEstudoMateriaHistorico entidade : entidades) {
			beans.add(this.convert(beanPai, entidade));
		}

		return beans;
	}

}
