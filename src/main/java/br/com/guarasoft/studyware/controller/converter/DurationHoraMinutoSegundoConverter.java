package br.com.guarasoft.studyware.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

@FacesConverter("durationhhmmssConverter")
public class DurationHoraMinutoSegundoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		PeriodFormatter formatter = new PeriodFormatterBuilder().printZeroAlways().minimumPrintedDigits(2).appendHours().appendSeparator(":").printZeroAlways().minimumPrintedDigits(2).appendMinutes()
				.appendSeparator(":").printZeroAlways().minimumPrintedDigits(2).appendSeconds().toFormatter();
		return formatter.print(((Duration) value).toPeriod());
	}

}
