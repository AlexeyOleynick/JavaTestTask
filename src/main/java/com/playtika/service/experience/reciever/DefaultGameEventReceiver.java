package com.playtika.service.experience.reciever;

import com.playtika.service.experience.dao.ExperienceDao;
import com.playtika.service.experience.dao.dto.StatisticDto;
import com.playtika.service.experience.event.GameEvent;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/10/13
 * Time: 7:03 PM
*/
public class DefaultGameEventReceiver implements GameEventReceiver {

	private ExperienceDao experienceDao;
	private Map<Integer, UserData> userData;

	public DefaultGameEventReceiver() {
		userData = new HashMap<Integer, UserData>();
	}

	@Override
	public void processEvent(GameEvent event) {
		switch (event.type) {
			case StartGame:
				userData.put(event.userId, new UserData(event.timestamp, event.experience));
				break;
			case Experience:
				userData.get(event.userId).increaseExperience(event.experience);
				break;
			case FinishGame:
				int aggregatedExperience = userData.get(event.userId).getExperience();
				experienceDao.addExperienceRecord(new StatisticDto(event.userId, event.timestamp, aggregatedExperience));
				userData.remove(event.userId);
				break;
		}
	}

	@Inject
	@Override
	public void setDao(ExperienceDao experienceDao) {
		this.experienceDao = experienceDao;
	}

	class UserData {

		private int timestamp;
		private int experience;

		public UserData(int timestamp, int experience) {
			this.timestamp = timestamp;
			this.experience = experience;
		}

		public void increaseExperience(int experience) {
			this.experience += experience;
		}

		public int getExperience() {
			return experience;
		}

	}


}