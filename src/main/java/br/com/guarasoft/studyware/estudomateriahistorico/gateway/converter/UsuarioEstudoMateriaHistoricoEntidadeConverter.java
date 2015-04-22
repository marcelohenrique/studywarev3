package br.com.guarasoft.studyware.estudomateriahistorico.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateria.gateway.converter.UsuarioEstudoMateriaBuilder;
import br.com.guarasoft.studyware.estudomateriahistorico.bean.UsuarioEstudoMateriaHistoricoBean;
import br.com.guarasoft.studyware.estudomateriahistorico.gateway.entidade.EstudoMateriaHistoricoEntidade;

public class UsuarioEstudoMateriaHistoricoEntidadeConverter {

	private final UsuarioEstudoMateriaBuilder usuarioEstudoMateriaBuilder = new UsuarioEstudoMateriaBuilder();

	public EstudoMateriaHistoricoEntidade convert(UsuarioEstudoMateriaHistoricoBean bean) {
		EstudoMateriaHistoricoEntidade entidade = new EstudoMateriaHistoricoEntidade();

		entidade.setId(bean.getId());
		entidade.setEstudoMateria(this.usuarioEstudoMateriaBuilder.convert(bean
				.getEstudoMateria()));
		entidade.setHoraEstudo(bean.getHoraEstudo());
		entidade.setTempoEstudado(bean.getTempoEstudado().getMillis());
		entidade.setObservacao(bean.getObservacao());

		return entidade;
	}

	public UsuarioEstudoMateriaHistoricoBean convert(Estudo beanPai, EstudoMateriaHistoricoEntidade entidade) {
		UsuarioEstudoMateriaHistoricoBean bean = new UsuarioEstudoMateriaHistoricoBean();

		bean.setId(entidade.getId());
		bean.setEstudoMateria(this.usuarioEstudoMateriaBuilder.convert(beanPai,
				entidade.getEstudoMateria()));
		bean.setHoraEstudo(entidade.getHoraEstudo());
		bean.setTempoEstudado(new Duration(entidade.getTempoEstudado()));
		bean.setObservacao(entidade.getObservacao());

		return bean;
	}

	public List<UsuarioEstudoMateriaHistoricoBean> convert(Estudo beanPai, List<EstudoMateriaHistoricoEntidade> entidades) {
		List<UsuarioEstudoMateriaHistoricoBean> beans = new ArrayList<>();

		for (EstudoMateriaHistoricoEntidade entidade : entidades) {
			beans.add(this.convert(beanPai, entidade));
		}

		return beans;
	}

}
