/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concursomateria.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.guarasoft.conteudoprogramatico.concursomateria.persistence.ConcursoMateriaRepository;

/**
 * @author guara
 * 
 */
@Named("concursomateriaconverter")
public class ConcursoMateriaConverter implements Converter {

	@Inject
	private ConcursoMateriaRepository concursoMateriaRepository;

	/**
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
	 *      javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if( value == null || "".equals(value) ) {
			return value;
		}
		return concursoMateriaRepository.findById(Long.parseLong(value));
	}

	/**
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 *      , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return value.toString();
	}

}
