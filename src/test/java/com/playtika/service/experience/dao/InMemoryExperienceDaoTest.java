package com.playtika.service.experience.dao;

import com.playtika.service.experience.dao.dto.StatisticDto;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/11/13
 * Time: 1:22 AM
*/
public class InMemoryExperienceDaoTest {

	private final int firstUserId = 23;
	private final int secondUserId = 76;
	private final int thirdUserId = 55;

	private InMemoryExperienceDao inMemoryExperienceDao;

	@Before
	public void fillDao() {
		inMemoryExperienceDao = new InMemoryExperienceDao();
		inMemoryExperienceDao.addExperienceRecord(new StatisticDto(firstUserId, 1000, 100));
		inMemoryExperienceDao.addExperienceRecord(new StatisticDto(firstUserId, 2000, 200));
		inMemoryExperienceDao.addExperienceRecord(new StatisticDto(firstUserId, 3000, 150));

		inMemoryExperienceDao.addExperienceRecord(new StatisticDto(secondUserId, 2000, 200));
		inMemoryExperienceDao.addExperienceRecord(new StatisticDto(secondUserId, 3000, 150));
		inMemoryExperienceDao.addExperienceRecord(new StatisticDto(secondUserId, 5000, 300));
		inMemoryExperienceDao.addExperienceRecord(new StatisticDto(secondUserId, 7000, 100));

		inMemoryExperienceDao.addExperienceRecord(new StatisticDto(thirdUserId, 4000, 200));
		inMemoryExperienceDao.addExperienceRecord(new StatisticDto(thirdUserId, 8000, 150));
	}


	@Test
	public void dataShouldBeFetchedForOneUserInRange() {

		List<StatisticDto> userStatistic = inMemoryExperienceDao.fetchData(firstUserId, 0, 2500);

		assertEquals(userStatistic.get(0).userId, firstUserId);
		assertEquals(userStatistic.get(0).timestamp, 1000);
		assertEquals(userStatistic.get(0).experience, 100);

		assertEquals(userStatistic.get(1).userId, firstUserId);
		assertEquals(userStatistic.get(1).timestamp, 2000);
		assertEquals(userStatistic.get(1).experience, 200);

	}

	@Test
	public void correctAmountOfDataShouldBeFetched() {
		List<StatisticDto> allSecondUserStatistic = inMemoryExperienceDao.fetchData(secondUserId, 1000, 9000);
		assertEquals(allSecondUserStatistic.size(), 4);

		List<StatisticDto> partialThirdUserStatistic = inMemoryExperienceDao.fetchData(thirdUserId, 1000, 5000);
		assertEquals(partialThirdUserStatistic.size(), 1);

		List<StatisticDto> partialSecondUserStatistic = inMemoryExperienceDao.fetchData(secondUserId, 2500, 6000);
		assertEquals(partialSecondUserStatistic.size(), 2);
	}
}
