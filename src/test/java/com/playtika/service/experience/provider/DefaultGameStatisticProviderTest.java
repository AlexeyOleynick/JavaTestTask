package com.playtika.service.experience.provider;

import com.playtika.service.experience.dao.ExperienceDao;
import com.playtika.service.experience.dao.dto.StatisticDto;
import com.playtika.service.experience.provider.DefaultGameStatisticProvider;
import com.playtika.service.experience.provider.GameStatisticProvider;
import com.playtika.service.experience.provider.timestamp.TimestampProvider;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class DefaultGameStatisticProviderTest {

	@Test
	public void dataShouldBeFetchedFromDao() {
		GameStatisticProvider gameStatisticProvider = new DefaultGameStatisticProvider();
		ExperienceDao mockedDao = mock(ExperienceDao.class);
		gameStatisticProvider.setDao(mockedDao);

		int userId = 4234;
		long currentTimestamp = new Date().getTime();

		gameStatisticProvider.getStatistic(userId, currentTimestamp - 2000, currentTimestamp - 50);
		verify(mockedDao).fetchData(userId, currentTimestamp - 2000, currentTimestamp - 50);
	}

	@Test
	public void dataWasFetchedFromTimestampToTimestamp() {
		GameStatisticProvider gameStatisticProvider = new DefaultGameStatisticProvider();
		ExperienceDao mockedDao = mock(ExperienceDao.class);
		gameStatisticProvider.setDao(mockedDao);

		int userId = 4234;
		long currentTimestamp = new Date().getTime();

		List<StatisticDto> statisticSet = new ArrayList<StatisticDto>();
		statisticSet.add(new StatisticDto(userId, currentTimestamp - 1000, 100));
		statisticSet.add(new StatisticDto(userId, currentTimestamp - 500, 50));
		statisticSet.add(new StatisticDto(userId, currentTimestamp - 100, 300));

		when(mockedDao.fetchData(anyInt(), anyLong(), anyLong())).thenReturn(statisticSet);
		List<StatisticDto> statisticOutput = gameStatisticProvider.getStatistic(userId, currentTimestamp - 2000, currentTimestamp - 50);

		assertEquals(statisticSet, statisticOutput);
	}

	@Test
	public void dataWasFetchedFromTimestampToNow(){

		GameStatisticProvider gameStatisticProvider = new DefaultGameStatisticProvider();

		TimestampProvider mockedTimestampProvider = mock(TimestampProvider.class);
		gameStatisticProvider.setCurrentTimestampProvider(mockedTimestampProvider);

		long now = new Date().getTime();
		when(mockedTimestampProvider.getCurrentTimestamp()).thenReturn(now);

		GameStatisticProvider spy = spy(gameStatisticProvider);
		doReturn(null).when(spy).getStatistic(anyInt(), anyLong(), anyLong());

		int userId = 213;
		long fromTimestamp = now - 2000;

		spy.getStatistic(userId, fromTimestamp);
		verify(spy).getStatistic(userId, fromTimestamp, now);
	}
}
