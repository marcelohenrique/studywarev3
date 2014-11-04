package br.com.guarasoft.studyware.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class DurationConverter {

	private static final PeriodFormatter FORMATTER = new PeriodFormatterBuilder().printZeroAlways().minimumPrintedDigits(2).appendHours().appendSeparator(":").printZeroAlways()
			.minimumPrintedDigits(2).appendMinutes().toFormatter();
	private final SimpleDateFormat SDF = new SimpleDateFormat("HH:mm");

	public String toString(Duration duration) {
		return FORMATTER.print(duration.toPeriod());
	}

	public Date toDate(Duration duration) {
		String tempo = toString(duration);
		try {
			return SDF.parse(tempo);
		} catch (ParseException e) {
			throw new RuntimeException("DurationConverter => toDate: erro no parse da string.");
		}
	}

	public Duration toDuration(String value) {
		try {
			this.SDF.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date d = SDF.parse(value);
			this.SDF.setTimeZone(TimeZone.getDefault());
			DateTime dt = new DateTime(d, DateTimeZone.UTC);
			return new Duration(dt.getMillis());
		} catch (ParseException e) {
			throw new RuntimeException("DurationConverter => toDuration: erro no parse da string.");
		}
	}

	public Duration toDuration(Date date) {
		return toDuration(SDF.format(date));
	}

}
