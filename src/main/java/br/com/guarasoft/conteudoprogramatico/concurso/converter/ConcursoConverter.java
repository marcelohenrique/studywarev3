/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.concurso.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.guarasoft.conteudoprogramatico.concurso.persistence.ConcursoRepository;

/**
 * @author guara
 * 
 */
@Named("concursoconverter")
public class ConcursoConverter implements Converter {

	@Inject
	private ConcursoRepository concursoRepository;

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
		return concursoRepository.findById(Long.parseLong(value));
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
