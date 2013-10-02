/**
 * 
 */
package br.com.guarasoft.conteudoprogramatico.controleestudo.managedbean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * @author guara
 * 
 */
@FacesConverter("tempoConverter")
public class TempoConverter implements Converter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		PeriodFormatter formatter = new PeriodFormatterBuilder()
										.printZeroAlways().minimumPrintedDigits(2).appendHours().appendSeparator(":")
										.printZeroAlways().minimumPrintedDigits(2).appendMinutes().toFormatter();
		return formatter.print(((Duration) value).toPeriod());
	}

}
