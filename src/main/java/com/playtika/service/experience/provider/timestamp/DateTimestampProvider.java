package com.playtika.service.experience.provider.timestamp;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/11/13
 * Time: 12:08 AM
*/
public class DateTimestampProvider implements TimestampProvider {

	@Override
	public long getCurrentTimestamp() {
		Date now = new Date();
		return now.getTime();
	}
}
