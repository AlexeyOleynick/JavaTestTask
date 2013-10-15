package com.playtika.service.experience.event;


public class GameEvent {
	public final GameEventType type;
	public final int userId;
	public final int timestamp;
	public final int experience;

	public GameEvent(GameEventType type, int userId, int timestamp, int experience) {
		this.type = type;
		this.userId = userId;
		this.timestamp = timestamp;
		this.experience = experience;
	}
}
