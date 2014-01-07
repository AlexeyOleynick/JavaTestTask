package com.playtika.service.experience.reciever;

import com.playtika.service.experience.dao.ExperienceDao;
import com.playtika.service.experience.event.GameEvent;

/**
 * Created with IntelliJ IDEA.
 * User: OOliinyk
 * Date: 10/10/13
 * Time: 7:03 PM
*/
public interface GameEventReceiver {
	void processEvent(GameEvent event);
}
