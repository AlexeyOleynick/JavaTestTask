package com.playtika.service.experience.dao;

import com.playtika.service.experience.dao.dto.StatisticDto;

import java.util.List;

public interface ExperienceDao {

	void addExperienceRecord(StatisticDto statisticDto);

	List<StatisticDto> fetchData(int userId, long fromTimestamp, long toTimestamp);
}
