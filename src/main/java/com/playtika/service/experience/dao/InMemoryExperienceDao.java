package com.playtika.service.experience.dao;

import com.playtika.service.experience.dao.dto.StatisticDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/11/13
 * Time: 12:54 AM
*/
public class InMemoryExperienceDao implements ExperienceDao {

	private Map<Integer, List<StatisticDto>> warehouse;



	public InMemoryExperienceDao() {
		this.warehouse = new HashMap<Integer, List<StatisticDto>>();
	}

	@Override
	public void addExperienceRecord(StatisticDto statisticDto) {

		if (warehouse.containsKey(statisticDto.userId)) {
			List<StatisticDto> userRecords = warehouse.get(statisticDto.userId);
			userRecords.add(statisticDto);
		} else {
			List<StatisticDto> userRecords = new ArrayList<StatisticDto>();
			userRecords.add(statisticDto);
			warehouse.put(statisticDto.userId, userRecords);
		}
	}

	@Override
	public List<StatisticDto> fetchData(int userId, long fromTimestamp, long toTimestamp) {
		List<StatisticDto> userRecords = warehouse.get(userId);
		return filterStatistic(userRecords, fromTimestamp, toTimestamp);
	}

	private List<StatisticDto> filterStatistic(List<StatisticDto> rawStatistic, long fromTimestamp, long toTimeStamp) {
		List<StatisticDto> filteredStatistic = new ArrayList<StatisticDto>();
		for (StatisticDto statisticDto : rawStatistic) {
			if (statisticDto.timestamp > fromTimestamp && statisticDto.timestamp < toTimeStamp) {
				filteredStatistic.add(statisticDto);
			}
		}
		return filteredStatistic;
	}


}
