package br.com.guarasoft.studyware.estudo.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.materia.gateway.MateriaGateway;

@Named("materiaConverter")
public class UsuarioEstudoMateriaConverter implements Converter {

	private final Logger logger = LoggerFactory.getLogger(UsuarioEstudoMateriaConverter.class);

	@Inject
	private MateriaGateway materiaGateway;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if ((value == null) || "".equals(value)) {
			return value;
		}

		MateriaBean bean = this.materiaGateway.buscaPorId(Long.parseLong(value));

		this.logger.info(bean.toString());

		return bean;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if ("".equals(value)) {
			return "";
		}

		MateriaBean bean = (MateriaBean) value;

		String valor = bean.getId().toString();

		this.logger.info(valor);

		return valor;
	}

}
