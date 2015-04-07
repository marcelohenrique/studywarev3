package br.com.guarasoft.studyware.estudomateria.gateway.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.com.guarasoft.studyware.estudo.gateway.converter.EstudoEntidadeConverter;
import br.com.guarasoft.studyware.estudo.gateway.entidade.EstudoEntidade;
import br.com.guarasoft.studyware.estudo.modelo.Estudo;
import br.com.guarasoft.studyware.estudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.estudomateria.gateway.entidade.EstudoMateriaEntidade;
import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;

public class UsuarioEstudoMateriaBuilder {

	private final EstudoEntidadeConverter estudoEntidadeConverter = new EstudoEntidadeConverter();
	private final MateriaEntidadeConverter materiaEntidadeConverter = new MateriaEntidadeConverter();
	private final UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter = new UsuarioEstudoMateriaEntidadeConverter();

	public EstudoMateriaEntidade convert(UsuarioEstudoMateriaBean bean) {
		EstudoEntidade entidadePai = this.estudoEntidadeConverter.convert(bean.getEstudo());

		EstudoMateriaEntidade entidade = this.convert(entidadePai, bean);

		return entidade;
	}

	private EstudoMateriaEntidade convert(EstudoEntidade entidadePai, UsuarioEstudoMateriaBean bean) {
		EstudoMateriaEntidade entidade = this.usuarioEstudoMateriaEntidadeConverter.convert(entidadePai, bean);

		entidade.setMateria(this.materiaEntidadeConverter.convert(bean.getMateria()));

		return entidade;
	}

	public UsuarioEstudoMateriaBean convert(EstudoMateriaEntidade entidade) {
		UsuarioEstudoMateriaBean bean = this.usuarioEstudoMateriaEntidadeConverter.convert(entidade);

		bean.setEstudo(this.estudoEntidadeConverter.convert(entidade.getEstudo()));
		bean.setMateria(this.materiaEntidadeConverter.convert(entidade.getMateria()));

		return bean;
	}

	public UsuarioEstudoMateriaBean convert(Estudo beanPai, EstudoMateriaEntidade entidade) {
		UsuarioEstudoMateriaBean bean = this.usuarioEstudoMateriaEntidadeConverter.convert(beanPai, entidade);

		bean.setMateria(this.materiaEntidadeConverter.convert(entidade.getMateria()));

		return bean;
	}

	public Set<EstudoMateriaEntidade> convert(EstudoEntidade entidadePai, List<UsuarioEstudoMateriaBean> beans) {
		Set<EstudoMateriaEntidade> entidades = new LinkedHashSet<>();

		for (UsuarioEstudoMateriaBean bean : beans) {
			entidades.add(this.convert(entidadePai, bean));
		}

		return entidades;
	}

	public List<UsuarioEstudoMateriaBean> convert(Estudo beanPai, Collection<EstudoMateriaEntidade> entidades) {
		List<UsuarioEstudoMateriaBean> beans = new ArrayList<>();

		for (EstudoMateriaEntidade entidade : entidades) {
			if (entidade != null) {
				beans.add(this.convert(beanPai, entidade));
			}
		}

		return beans;
	}

}
