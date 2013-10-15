package com.playtika.service.experience.provider;

import com.playtika.service.experience.dao.ExperienceDao;
import com.playtika.service.experience.dao.dto.StatisticDto;
import com.playtika.service.experience.provider.timestamp.TimestampProvider;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/10/13
 * Time: 11:03 PM
*/
public interface GameStatisticProvider {
	void setDao(ExperienceDao mockedDao);

	List<StatisticDto> getStatistic(int userId, long fromTimestamp, long toTimestamp);

	List<StatisticDto> getStatistic(int userId, long fromTimestamp);

	void setCurrentTimestampProvider(TimestampProvider timestampProvider);
}
