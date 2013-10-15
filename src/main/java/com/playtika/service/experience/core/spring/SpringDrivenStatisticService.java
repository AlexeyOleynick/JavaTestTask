package com.playtika.service.experience.core.spring;

import com.playtika.service.experience.StatisticService;
import com.playtika.service.experience.provider.GameStatisticProvider;
import com.playtika.service.experience.reciever.GameEventReceiver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/11/13
 * Time: 2:05 AM
*/
public class SpringDrivenStatisticService implements StatisticService {

	private ApplicationContext context;

	public SpringDrivenStatisticService() {
		context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	}

	@Override
	public GameEventReceiver getEventReceiver() {
		return context.getBean(GameEventReceiver.class);
	}

	@Override
	public GameStatisticProvider getStatisticProvider() {
		return context.getBean(GameStatisticProvider.class);
	}


}
