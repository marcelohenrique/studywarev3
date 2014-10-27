package br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter.UsuarioEstudoMateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.usuarioestudomateriahistorico.gateway.entidade.UsuarioEstudoMateriaHistorico;

public class UsuarioEstudoMateriaHistoricoEntidadeConverter {

	@Inject
	private UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter;

	public UsuarioEstudoMateriaHistorico convert(
			UsuarioEstudoMateriaHistoricoBean bean) {
		UsuarioEstudoMateriaHistorico entidade = new UsuarioEstudoMateriaHistorico();

		entidade.setId(bean.getId());
		entidade.setUsuarioEstudoMateria(this.usuarioEstudoMateriaEntidadeConverter
				.convert(bean.getUsuarioEstudoMateria()));
		entidade.setHoraEstudo(bean.getHoraEstudo());
		entidade.setTempoEstudado(bean.getTempoEstudado().getMillis());
		entidade.setObservacao(bean.getObservacao());

		return entidade;
	}

	public List<UsuarioEstudoMateriaHistoricoBean> convert(
			List<UsuarioEstudoMateriaHistorico> entidades) {
		List<UsuarioEstudoMateriaHistoricoBean> beans = new ArrayList<>();

		for (UsuarioEstudoMateriaHistorico entidade : entidades) {
			beans.add(this.convert(entidade));
		}

		return beans;
	}

	public UsuarioEstudoMateriaHistoricoBean convert(
			UsuarioEstudoMateriaHistorico entidade) {
		UsuarioEstudoMateriaHistoricoBean bean = new UsuarioEstudoMateriaHistoricoBean();

		bean.setId(entidade.getId());
		bean.setUsuarioEstudoMateria(this.usuarioEstudoMateriaEntidadeConverter
				.convert(entidade.getUsuarioEstudoMateria()));
		bean.setHoraEstudo(entidade.getHoraEstudo());
		bean.setTempoEstudado(new Duration(entidade.getTempoEstudado()));
		bean.setObservacao(entidade.getObservacao());

		return bean;
	}

}
