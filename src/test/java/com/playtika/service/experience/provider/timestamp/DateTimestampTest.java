package com.playtika.service.experience.provider.timestamp;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/11/13
 * Time: 12:03 AM
*/
public class DateTimestampTest {

	@Test
	public void currentTimestampIsCorrect() {
		TimestampProvider dateTimestampProvider = new DateTimestampProvider();

		Date now = new Date();

		assertTrue(dateTimestampProvider.getCurrentTimestamp() >= now.getTime());
	}

}
