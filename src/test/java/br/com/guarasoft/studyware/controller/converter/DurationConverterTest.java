package br.com.guarasoft.studyware.controller.converter;

import static org.junit.Assert.*;

import org.joda.time.Duration;
import org.junit.Test;

public class DurationConverterTest {

	@Test
	public void testDurationConverter() {
		DurationConverter durationConverter = new DurationConverter();

		assertNotNull(durationConverter);
	}

	@Test
	public void testDurationConverterComPattern() {
		String datePattern = "HH:mm";

		DurationConverter durationConverter = new DurationConverter(datePattern);

		assertNotNull(durationConverter);
	}

	@Test
	public void testToStringDuration() {
		DurationConverter durationConverter = new DurationConverter();
		
		String tempo = durationConverter.toString(new Duration(0L));
		
		assertNotNull(tempo);
	}

//	@Test
//	public void testToDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testToDurationString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testToDurationDate() {
//		fail("Not yet implemented");
//	}

}
