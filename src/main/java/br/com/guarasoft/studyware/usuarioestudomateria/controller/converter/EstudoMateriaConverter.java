package br.com.guarasoft.studyware.usuarioestudomateria.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.guarasoft.studyware.usuarioestudomateria.gateway.UsuarioEstudoMateriaGateway;

@Named("estudomateriaconverter")
public class EstudoMateriaConverter implements Converter {

	@Inject
	private UsuarioEstudoMateriaGateway usuarioEstudoMateriaGateway;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if( value == null || "".equals(value) ) {
			return value;
		}
		return usuarioEstudoMateriaGateway.findById(Long.parseLong(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return value.toString();
	}

}
