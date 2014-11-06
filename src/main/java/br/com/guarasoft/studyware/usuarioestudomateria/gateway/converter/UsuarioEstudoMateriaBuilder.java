package br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.converter.UsuarioEstudoEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

public class UsuarioEstudoMateriaBuilder {

	private final UsuarioEstudoEntidadeConverter usuarioEstudoEntidadeConverter = new UsuarioEstudoEntidadeConverter();
	private final MateriaEntidadeConverter materiaEntidadeConverter = new MateriaEntidadeConverter();
	private final UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter = new UsuarioEstudoMateriaEntidadeConverter();

	public UsuarioEstudoMateria convert(UsuarioEstudoMateriaBean bean) {
		UsuarioEstudo entidadePai = this.usuarioEstudoEntidadeConverter.convert(bean.getUsuarioEstudo());

		UsuarioEstudoMateria entidade = this.convert(entidadePai, bean);

		return entidade;
	}

	private UsuarioEstudoMateria convert(UsuarioEstudo entidadePai, UsuarioEstudoMateriaBean bean) {
		UsuarioEstudoMateria entidade = this.usuarioEstudoMateriaEntidadeConverter.convert(entidadePai, bean);

		entidade.setMateria(this.materiaEntidadeConverter.convert(bean.getMateria()));

		return entidade;
	}

	public UsuarioEstudoMateriaBean convert(UsuarioEstudoMateria entidade) {
		UsuarioEstudoMateriaBean bean = this.usuarioEstudoMateriaEntidadeConverter.convert(entidade);

		bean.setUsuarioEstudo(this.usuarioEstudoEntidadeConverter.convert(entidade.getUsuarioEstudo()));
		bean.setMateria(this.materiaEntidadeConverter.convert(entidade.getMateria()));

		return bean;
	}

	public UsuarioEstudoMateriaBean convert(UsuarioEstudoBean beanPai, UsuarioEstudoMateria entidade) {
		UsuarioEstudoMateriaBean bean = this.usuarioEstudoMateriaEntidadeConverter.convert(beanPai, entidade);

		bean.setMateria(this.materiaEntidadeConverter.convert(entidade.getMateria()));

		return bean;
	}

	public List<UsuarioEstudoMateria> convert(UsuarioEstudo entidadePai, List<UsuarioEstudoMateriaBean> beans) {
		List<UsuarioEstudoMateria> entidades = new ArrayList<>();

		for (UsuarioEstudoMateriaBean bean : beans) {
			entidades.add(this.convert(entidadePai, bean));
		}

		return entidades;
	}

	public List<UsuarioEstudoMateriaBean> convert(UsuarioEstudoBean beanPai, List<UsuarioEstudoMateria> entidades) {
		List<UsuarioEstudoMateriaBean> beans = new ArrayList<>();

		for (UsuarioEstudoMateria entidade : entidades) {
			if (entidade != null) {
				beans.add(this.convert(beanPai, entidade));
			}
		}

		return beans;
	}

}
