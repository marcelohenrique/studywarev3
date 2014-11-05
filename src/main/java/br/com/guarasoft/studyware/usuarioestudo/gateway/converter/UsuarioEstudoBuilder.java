package br.com.guarasoft.studyware.usuarioestudo.gateway.converter;

import java.util.List;

import br.com.guarasoft.studyware.estudodiario.gateway.converter.UsuarioEstudoDiarioEntidadeConverter;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudo.gateway.entidade.UsuarioEstudo;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.converter.UsuarioEstudoMateriaEntidadeConverter;

public class UsuarioEstudoBuilder {

	private final UsuarioEstudoEntidadeConverter usuarioEstudoEntidadeConverter = new UsuarioEstudoEntidadeConverter();

	private final UsuarioEstudoMateriaEntidadeConverter usuarioEstudoMateriaEntidadeConverter = new UsuarioEstudoMateriaEntidadeConverter();;
	private final UsuarioEstudoDiarioEntidadeConverter estudoDiarioConverter = new UsuarioEstudoDiarioEntidadeConverter();

	private boolean converteMaterias;
	private boolean converteDias;

	public UsuarioEstudoBuilder converteMaterias() {
		this.converteMaterias = true;
		return this;
	}

	public UsuarioEstudoBuilder converteDias() {
		this.converteDias = true;
		return this;
	}

	public UsuarioEstudoBean convert(UsuarioEstudo entidade) {
		UsuarioEstudoBean bean = this.usuarioEstudoEntidadeConverter.convert(entidade);

		if (this.converteMaterias) {
			bean.setMaterias(this.usuarioEstudoMateriaEntidadeConverter.convert(bean, entidade.getMaterias()));
		}

		if (this.converteDias) {
			bean.setDias(this.estudoDiarioConverter.convert(bean, entidade.getUsuarioEstudoDiarios()));
		}

		return bean;
	}

	public UsuarioEstudo convert(UsuarioEstudoBean bean) {
		UsuarioEstudo entidade = this.usuarioEstudoEntidadeConverter.convert(bean);

		if (this.converteMaterias) {
			entidade.setMaterias(this.usuarioEstudoMateriaEntidadeConverter.convert(entidade, bean.getMaterias()));
		}

		if (this.converteDias) {
			entidade.setUsuarioEstudoDiarios(this.estudoDiarioConverter.convert(entidade, bean.getDias()));
		}

		return entidade;
	}

	public List<UsuarioEstudoBean> convert(List<UsuarioEstudo> entidades) {
		List<UsuarioEstudoBean> beans = this.usuarioEstudoEntidadeConverter.convert(entidades);

		return beans;
	}

}
