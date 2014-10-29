package br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudo.gateway.converter.UsuarioEstudoEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateriaPK;

public class UsuarioEstudoMateriaEntidadeConverter {

	@Inject
	private UsuarioEstudoEntidadeConverter usuarioEstudoEntidadeConverter;
	@Inject
	private MateriaEntidadeConverter materiaEntidadeConverter;

	public UsuarioEstudoMateriaBean convert(UsuarioEstudoMateria entidade) {
		UsuarioEstudoMateriaBean bean = new UsuarioEstudoMateriaBean();

		bean.setUsuarioEstudoBean(this.usuarioEstudoEntidadeConverter
				.convert(entidade.getPk().getUsuarioEstudo()));
		bean.setMateriaBean(this.materiaEntidadeConverter.convert(entidade
				.getPk().getMateria()));
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setOrdem(entidade.getOrdem());

		return bean;
	}

	public UsuarioEstudoMateria convert(UsuarioEstudoMateriaBean bean) {
		UsuarioEstudoMateriaPK pk = new UsuarioEstudoMateriaPK();
		pk.setUsuarioEstudo(this.usuarioEstudoEntidadeConverter.convert(bean
				.getUsuarioEstudoBean()));
		pk.setMateria(this.materiaEntidadeConverter.convert(bean
				.getMateriaBean()));

		UsuarioEstudoMateria entidade = new UsuarioEstudoMateria();
		entidade.setPk(pk);
		entidade.setTempoAlocado(bean.getTempoAlocado().getMillis());
		entidade.setOrdem(bean.getOrdem());

		return entidade;
	}

	public List<UsuarioEstudoMateriaBean> converteEntidadesParaBeans(
			List<UsuarioEstudoMateria> entidades) {
		List<UsuarioEstudoMateriaBean> beans = new ArrayList<>();

		for (UsuarioEstudoMateria entidade : entidades) {
			beans.add(convert(entidade));
		}
		return beans;
	}

	public List<UsuarioEstudoMateria> converteBeansParaEntidades(
			List<UsuarioEstudoMateriaBean> beans) {
		List<UsuarioEstudoMateria> entidades = new ArrayList<>();
		return entidades;
	}

}
