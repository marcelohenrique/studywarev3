package br.com.guarasoft.studyware.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

@FacesConverter("durationhhmmConverter")
public class DurationHoraMinutoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if ("".equals(value)) {
			return null;
		}
		return new DurationConverterHelper("HH:mm").toDuration(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		PeriodFormatterBuilder builder = new PeriodFormatterBuilder();
		builder.printZeroAlways().minimumPrintedDigits(2).appendHours();
		builder.appendSeparator(":");
		builder.printZeroAlways().minimumPrintedDigits(2).appendMinutes();
		PeriodFormatter formatter = builder.toFormatter();
		return formatter.print(((Duration) value).toPeriod());
	}

}
