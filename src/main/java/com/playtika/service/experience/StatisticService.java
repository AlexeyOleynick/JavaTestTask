package com.playtika.service.experience;

import com.playtika.service.experience.provider.GameStatisticProvider;
import com.playtika.service.experience.reciever.GameEventReceiver;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/11/13
 * Time: 2:04 AM
*/
public interface StatisticService {

	GameEventReceiver getEventReceiver();

	GameStatisticProvider getStatisticProvider();

}
