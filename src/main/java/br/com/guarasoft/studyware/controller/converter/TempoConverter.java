package br.com.guarasoft.studyware.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.Duration;

@FacesConverter("tempoConverter")
public class TempoConverter implements Converter {

	private final DurationConverterHelper converter = new DurationConverterHelper();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return this.converter.toDuration(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return this.converter.toString((Duration) value);
	}

}
