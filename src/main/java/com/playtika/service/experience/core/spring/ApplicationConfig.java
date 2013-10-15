package com.playtika.service.experience.core.spring;

import com.playtika.service.experience.dao.ExperienceDao;
import com.playtika.service.experience.dao.InMemoryExperienceDao;
import com.playtika.service.experience.provider.DefaultGameStatisticProvider;
import com.playtika.service.experience.provider.GameStatisticProvider;
import com.playtika.service.experience.provider.timestamp.DateTimestampProvider;
import com.playtika.service.experience.provider.timestamp.TimestampProvider;
import com.playtika.service.experience.reciever.DefaultGameEventReceiver;
import com.playtika.service.experience.reciever.GameEventReceiver;
import org.springframework.context.annotation.Bean;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/11/13
 * Time: 2:29 AM
*/
public class ApplicationConfig {

	@Bean
	GameEventReceiver getMessageService() {
		return new DefaultGameEventReceiver();
	}

	@Bean
	GameStatisticProvider getStatisticProvider() {
		return new DefaultGameStatisticProvider();
	}

	@Bean
	TimestampProvider getTimestampProvider() {
		return new DateTimestampProvider();
	}

	@Bean
	ExperienceDao getExperienceDao() {
		return new InMemoryExperienceDao();
	}

}
