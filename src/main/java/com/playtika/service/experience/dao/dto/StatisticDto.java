package com.playtika.service.experience.dao.dto;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/10/13
 * Time: 11:00 PM
*/
public class StatisticDto {
	public final int userId;
	public final int experience;
	public final long timestamp;

	public StatisticDto(int userId, long timestamp, int experience) {
		this.userId = userId;
		this.experience = experience;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "userId = " + userId + "; " + "experience = " + experience + "; " + "timestamp = " + timestamp + "; ";
	}
}
