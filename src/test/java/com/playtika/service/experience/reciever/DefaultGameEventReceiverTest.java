package com.playtika.service.experience.reciever;

import com.playtika.service.experience.dao.ExperienceDao;
import com.playtika.service.experience.dao.dto.StatisticDto;
import com.playtika.service.experience.event.GameEvent;
import com.playtika.service.experience.event.GameEventType;
import com.playtika.service.experience.reciever.DefaultGameEventReceiver;
import com.playtika.service.experience.reciever.GameEventReceiver;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junitparams.JUnitParamsRunner.$;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JUnitParamsRunner.class)
public class DefaultGameEventReceiverTest {

	@Parameters(method = "provideTestData")
	@Test
	public void receiverShouldStoreCorrectDataToDao(List<GameEvent> events, int expectedExperience, int expectedTimestamp, int userId) {
		GameEventReceiver gameEventReceiver = new DefaultGameEventReceiver();
		ExperienceDao mockedDao = mock(ExperienceDao.class);
		gameEventReceiver.setDao(mockedDao);
		for (GameEvent event : events) {
			gameEventReceiver.processEvent(event);
		}
		verify(mockedDao).addExperienceRecord(refEq(new StatisticDto(userId, expectedTimestamp, expectedExperience)));
	}


	@Test
	public void receiverShouldStoreCorrectDataToDaoForMultipleUsers() {

		int firstUserID = 230491;
		int secondUserId = 239482;

		List<GameEvent> mergedUsersData = getShuffledEventHistory(firstUserID, secondUserId);

		GameEventReceiver gameEventReceiver = new DefaultGameEventReceiver();
		ExperienceDao mockedDao = mock(ExperienceDao.class);
		gameEventReceiver.setDao(mockedDao);

		for (GameEvent event : mergedUsersData) {
			gameEventReceiver.processEvent(event);
		}
		verify(mockedDao).addExperienceRecord(refEq(new StatisticDto(firstUserID, 23434664, 250)));
		verify(mockedDao).addExperienceRecord(refEq(new StatisticDto(secondUserId, 23434996, 750)));
	}


	public Object[] provideTestData() {
		return $(
				$(get500ExperienceEventSet(542), 500, 23434567, 542),
				$(get300ExperienceEventSet(550), 300, 23434599, 550)
		);
	}


	private List<GameEvent> getShuffledEventHistory(int firstUserId, int secondUserId) {

		ArrayList<GameEvent> events = new ArrayList<GameEvent>();
		events.add(createGameEvent(GameEventType.StartGame, firstUserId, 23434534, 0));
		events.add(createGameEvent(GameEventType.Experience, firstUserId, 23434534, 150));
		events.add(createGameEvent(GameEventType.StartGame, secondUserId, 23434534, 300));
		events.add(createGameEvent(GameEventType.Experience, secondUserId, 23434534, 400));
		events.add(createGameEvent(GameEventType.Experience, firstUserId, 23434534, 100));
		events.add(createGameEvent(GameEventType.Experience, secondUserId, 23434534, 50));
		events.add(createGameEvent(GameEventType.FinishGame, firstUserId, 23434664, 0));
		events.add(createGameEvent(GameEventType.FinishGame, secondUserId, 23434996, 0));

		return events;

	}

	private List<GameEvent> get500ExperienceEventSet(int userId) {
		ArrayList<GameEvent> events = new ArrayList<GameEvent>();
		events.add(createGameEvent(GameEventType.StartGame, userId, 23434534, 0));
		events.add(createGameEvent(GameEventType.Experience, userId, 23434554, 125));
		events.add(createGameEvent(GameEventType.Experience, userId, 23434556, 125));
		events.add(createGameEvent(GameEventType.Experience, userId, 23434559, 250));
		events.add(createGameEvent(GameEventType.FinishGame, userId, 23434567, 0));
		return events;
	}

	private List<GameEvent> get300ExperienceEventSet(int userId) {
		ArrayList<GameEvent> events = new ArrayList<GameEvent>();
		events.add(createGameEvent(GameEventType.StartGame, userId, 23434534, 0));
		events.add(createGameEvent(GameEventType.Experience, userId, 23434554, 130));
		events.add(createGameEvent(GameEventType.Experience, userId, 23434556, 170));
		events.add(createGameEvent(GameEventType.FinishGame, userId, 23434599, 0));
		return events;
	}

	private GameEvent createGameEvent(GameEventType type, int userId, int timestamp, int experience) {
		GameEvent gameEvent = new GameEvent(type, userId, timestamp, experience);
		return gameEvent;
	}


}
