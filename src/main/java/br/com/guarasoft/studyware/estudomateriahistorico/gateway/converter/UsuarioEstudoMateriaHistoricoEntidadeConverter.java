package br.com.guarasoft.studyware.estudomateriahistorico.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateria.gateway.converter.UsuarioEstudoMateriaBuilder;
import br.com.guarasoft.studyware.estudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.UsuarioEstudoMateriaHistorico;

public class UsuarioEstudoMateriaHistoricoEntidadeConverter {

	private final UsuarioEstudoMateriaBuilder usuarioEstudoMateriaBuilder = new UsuarioEstudoMateriaBuilder();

	public UsuarioEstudoMateriaHistorico convert(UsuarioEstudoMateriaHistoricoBean bean) {
		UsuarioEstudoMateriaHistorico entidade = new UsuarioEstudoMateriaHistorico();

		entidade.setId(bean.getId());
		entidade.setUsuarioEstudoMateria(this.usuarioEstudoMateriaBuilder.convert(bean.getUsuarioEstudoMateria()));
		entidade.setHoraEstudo(bean.getHoraEstudo());
		entidade.setTempoEstudado(bean.getTempoEstudado().getMillis());
		entidade.setObservacao(bean.getObservacao());

		return entidade;
	}

	public UsuarioEstudoMateriaHistoricoBean convert(Estudo beanPai, UsuarioEstudoMateriaHistorico entidade) {
		UsuarioEstudoMateriaHistoricoBean bean = new UsuarioEstudoMateriaHistoricoBean();

		bean.setId(entidade.getId());
		bean.setUsuarioEstudoMateria(this.usuarioEstudoMateriaBuilder.convert(beanPai, entidade.getUsuarioEstudoMateria()));
		bean.setHoraEstudo(entidade.getHoraEstudo());
		bean.setTempoEstudado(new Duration(entidade.getTempoEstudado()));
		bean.setObservacao(entidade.getObservacao());

		return bean;
	}

	public List<UsuarioEstudoMateriaHistoricoBean> convert(Estudo beanPai, List<UsuarioEstudoMateriaHistorico> entidades) {
		List<UsuarioEstudoMateriaHistoricoBean> beans = new ArrayList<>();

		for (UsuarioEstudoMateriaHistorico entidade : entidades) {
			beans.add(this.convert(beanPai, entidade));
		}

		return beans;
	}

}
