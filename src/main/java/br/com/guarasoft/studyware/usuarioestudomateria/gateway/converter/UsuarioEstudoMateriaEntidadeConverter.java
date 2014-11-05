package br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Duration;

import br.com.guarasoft.studyware.materia.gateway.converter.MateriaEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.converter.UsuarioEstudoEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.entidade.UsuarioEstudoMateria;

public class UsuarioEstudoMateriaEntidadeConverter {

	private final UsuarioEstudoEntidadeConverter usuarioEstudoEntidadeConverter = new UsuarioEstudoEntidadeConverter();
	private final MateriaEntidadeConverter materiaEntidadeConverter = new MateriaEntidadeConverter();

	public UsuarioEstudoMateriaBean convert(UsuarioEstudoBean beanPai, UsuarioEstudoMateria entidade) {
		UsuarioEstudoMateriaBean bean = new UsuarioEstudoMateriaBean();

		bean.setId(entidade.getId());
		bean.setUsuarioEstudo(beanPai);
		bean.setMateria(this.materiaEntidadeConverter.convert(entidade.getMateria()));
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setOrdem(entidade.getOrdem());

		return bean;
	}

	public UsuarioEstudoMateria convert(UsuarioEstudoMateriaBean bean) {
		UsuarioEstudo entidadePai = this.usuarioEstudoEntidadeConverter.convert(bean.getUsuarioEstudo());

		UsuarioEstudoMateria entidade = this.convert(entidadePai, bean);

		return entidade;
	}

	public UsuarioEstudoMateriaBean convert(UsuarioEstudoMateria entidade) {
		UsuarioEstudoMateriaBean bean = new UsuarioEstudoMateriaBean();

		bean.setId(entidade.getId());
		bean.setUsuarioEstudo(this.usuarioEstudoEntidadeConverter.convert(entidade.getUsuarioEstudo()));
		bean.setMateria(this.materiaEntidadeConverter.convert(entidade.getMateria()));
		bean.setTempoAlocado(new Duration(entidade.getTempoAlocado()));
		bean.setOrdem(entidade.getOrdem());

		return bean;
	}

	private UsuarioEstudoMateria convert(UsuarioEstudo entidadePai, UsuarioEstudoMateriaBean bean) {
		UsuarioEstudoMateria entidade = new UsuarioEstudoMateria();

		entidade.setId(bean.getId());
		entidade.setUsuarioEstudo(entidadePai);
		entidade.setMateria(this.materiaEntidadeConverter.convert(bean.getMateria()));
		entidade.setTempoAlocado(bean.getTempoAlocado().getMillis());
		entidade.setOrdem(bean.getOrdem());

		return entidade;
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
