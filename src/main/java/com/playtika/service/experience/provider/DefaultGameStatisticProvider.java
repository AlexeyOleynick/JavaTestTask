package com.playtika.service.experience.provider;

import com.playtika.service.experience.dao.ExperienceDao;
import com.playtika.service.experience.dao.dto.StatisticDto;
import com.playtika.service.experience.provider.timestamp.TimestampProvider;

import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/10/13
 * Time: 11:13 PM
*/
public class DefaultGameStatisticProvider implements GameStatisticProvider {

	private ExperienceDao experienceDao;

	private TimestampProvider timestampProvider;

	@Override
	public List<StatisticDto> getStatistic(int userId, long fromTimestamp, long toTimestamp) {
		return experienceDao.fetchData(userId, fromTimestamp, toTimestamp);
	}

	@Override
	public List<StatisticDto> getStatistic(int userId, long fromTimestamp) {
		long currentTimestamp = timestampProvider.getCurrentTimestamp();
		return getStatistic(userId, fromTimestamp, currentTimestamp);
	}

	@Inject
	@Override
	public void setDao(ExperienceDao experienceDao) {
		this.experienceDao = experienceDao;
	}

	@Inject
	@Override
	public void setCurrentTimestampProvider(TimestampProvider timestampProvider) {
		this.timestampProvider = timestampProvider;
	}
}
