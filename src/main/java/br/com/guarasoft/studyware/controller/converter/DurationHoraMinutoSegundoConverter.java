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
		return new DurationConverter("HH:mm:ss").toDuration(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		PeriodFormatterBuilder builder = new PeriodFormatterBuilder();
		builder.printZeroAlways().minimumPrintedDigits(2).appendHours();
		builder.appendSeparator(":");
		builder.printZeroAlways().minimumPrintedDigits(2).appendMinutes();
		builder.appendSeparator(":");
		builder.printZeroAlways().minimumPrintedDigits(2).appendSeconds();
		PeriodFormatter formatter = builder.toFormatter();
		return formatter.print(((Duration) value).toPeriod());
	}

}
