package br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.com.guarasoft.studyware.estudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.estudo.gateway.converter.UsuarioEstudoEntidadeConverter;
import br.com.guarasoft.studyware.estudo.gateway.entidade.UsuarioEstudo;
import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
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

	public Set<UsuarioEstudoMateria> convert(UsuarioEstudo entidadePai, List<UsuarioEstudoMateriaBean> beans) {
		Set<UsuarioEstudoMateria> entidades = new LinkedHashSet<>();

		for (UsuarioEstudoMateriaBean bean : beans) {
			entidades.add(this.convert(entidadePai, bean));
		}

		return entidades;
	}

	public List<UsuarioEstudoMateriaBean> convert(UsuarioEstudoBean beanPai, Collection<UsuarioEstudoMateria> entidades) {
		List<UsuarioEstudoMateriaBean> beans = new ArrayList<>();

		for (UsuarioEstudoMateria entidade : entidades) {
			if (entidade != null) {
				beans.add(this.convert(beanPai, entidade));
			}
		}

		return beans;
	}

}
