package com.playtika.service.experience.core.spring;

import com.playtika.service.experience.StatisticService;
import com.playtika.service.experience.dao.dto.StatisticDto;
import com.playtika.service.experience.event.GameEvent;
import com.playtika.service.experience.event.GameEventType;
import com.playtika.service.experience.provider.GameStatisticProvider;
import com.playtika.service.experience.reciever.GameEventReceiver;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/12/13
 * Time: 12:25 AM
 */
public class SpringDrivenStatisticServiceTest {

	@Test
	public void springShouldInjectAllDependencies(){

		StatisticService statisticService = new SpringDrivenStatisticService();

		GameEventReceiver eventReceiver = statisticService.getEventReceiver();
		GameStatisticProvider gameStatisticProvider = statisticService.getStatisticProvider();

		List<GameEvent> eventHistory = getEventHistory();
		for (GameEvent gameEvent : eventHistory) {
			eventReceiver.processEvent(gameEvent);
		}

		List<StatisticDto> stats = gameStatisticProvider.getStatistic(44, 0, 5000);
		assertEquals(stats.get(0).experience, 2900);
		assertEquals(stats.get(0).userId, 44);
		assertEquals(stats.get(0).timestamp, 4000);

	}

	private List<GameEvent> getEventHistory(){
		GameEvent event1 = new GameEvent(GameEventType.StartGame, 44, 1000, 0);
		GameEvent event2 = new GameEvent(GameEventType.Experience, 44, 2000, 2300);
		GameEvent event3 = new GameEvent(GameEventType.Experience, 44, 3000, 600);
		GameEvent event4 = new GameEvent(GameEventType.FinishGame, 44, 4000, 0);

		List<GameEvent> eventHistory = new ArrayList<GameEvent>();

		eventHistory.add(event1);
		eventHistory.add(event2);
		eventHistory.add(event3);
		eventHistory.add(event4);

		return eventHistory;
	}

}
