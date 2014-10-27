package br.com.guarasoft.studyware.usuarioestudomateria.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.guarasoft.studyware.materia.bean.MateriaBean;
import br.com.guarasoft.studyware.usuarioestudo.bean.UsuarioEstudoBean;
import br.com.guarasoft.studyware.usuarioestudomateria.bean.UsuarioEstudoMateriaBean;
import br.com.guarasoft.studyware.usuarioestudomateria.gateway.UsuarioEstudoMateriaGateway;

@Named("estudomateriaconverter")
public class UsuarioEstudoMateriaConverter implements Converter {

	@Inject
	private UsuarioEstudoMateriaGateway usuarioEstudoMateriaGateway;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || "".equals(value)) {
			return value;
		}

		String[] values = value.split("-");

		UsuarioEstudoBean usuarioEstudoBean = new UsuarioEstudoBean();
		usuarioEstudoBean.setEmail(values[0]);

		MateriaBean materiaBean = new MateriaBean();
		materiaBean.setId(Long.parseLong(values[1]));

		return usuarioEstudoMateriaGateway.find(usuarioEstudoBean, materiaBean);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if ("".equals(value)) {
			return "";
		}
		UsuarioEstudoMateriaBean bean = (UsuarioEstudoMateriaBean) value;
		return bean.getUsuarioEstudoBean().getEmail() + "-"
				+ bean.getMateriaBean().getId();
	}

}
