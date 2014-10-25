package br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudo.gateway.converter.UsuarioEstudoEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

public class UsuarioEstudoMateriaEntidadeConverter {

	@Inject
	private UsuarioEstudoEntidadeConverter usuarioEstudoEntidadeConverter;
	@Inject
	private MateriaEntidadeConverter materiaEntidadeConverter;

	public UsuarioEstudoMateriaBean convert(UsuarioEstudoMateria entidade) {
		UsuarioEstudoMateriaBean bean = new UsuarioEstudoMateriaBean();

		bean.setUsuarioEstudoBean(this.usuarioEstudoEntidadeConverter
				.convert(entidade.getUsuarioEstudoMateriaPK()
						.getUsuarioEstudo()));
		bean.setMateriaBean(this.materiaEntidadeConverter.convert(entidade
				.getUsuarioEstudoMateriaPK().getMateria()));
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setOrdem(entidade.getOrdem());

		return bean;
	}

	public List<UsuarioEstudoMateriaBean> convert(
			List<UsuarioEstudoMateria> entidades) {
		List<UsuarioEstudoMateriaBean> beans = new ArrayList<>();

		for (UsuarioEstudoMateria entidade : entidades) {
			beans.add(convert(entidade));
		}
		return beans;
	}

}
