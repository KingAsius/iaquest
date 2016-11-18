package com.botscrew.iaquest.controllers.Impl;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.botscrew.iaquest.api.ApiContainer;
import com.botscrew.iaquest.controllers.MainController;
import com.botscrew.iaquest.model.MeetingConfirmation;
import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;
import com.botscrew.iaquest.model.NotificationMessage;

public class IaquestController implements MainController {

	@Autowired
	private ApiContainer apiContainer;

	private LinkedList<NotificationMessage> notifications;

	@Override
	public Message offerNewMeeting(MeetingRequest command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message scheduleNewMeeting(MeetingConfirmation postback) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Scheduled(fixedDelay = 3000)
	public void sendNotifications() {
		LocalDateTime now = LocalDateTime.now();
		Iterator<NotificationMessage> iterator = notifications.iterator();
		NotificationMessage message;
		while (iterator.hasNext()) {
			message = iterator.next();
			if (message.getTimeOfNotification().isAfter(now)) {
				apiContainer.sendMessage(message);
				iterator.remove();
			}
		}
	}

}
