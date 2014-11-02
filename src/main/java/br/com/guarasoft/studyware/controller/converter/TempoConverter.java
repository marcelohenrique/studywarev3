package br.com.guarasoft.studyware.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

@FacesConverter("tempoConverter")
public class TempoConverter implements Converter {

	private static final PeriodFormatter FORMATTER = new PeriodFormatterBuilder().printZeroAlways().minimumPrintedDigits(2).appendHours().appendSeparator(":").printZeroAlways()
			.minimumPrintedDigits(2).appendMinutes().toFormatter();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return new Duration(FORMATTER.parsePeriod(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return FORMATTER.print(((Duration) value).toPeriod());
	}

}
