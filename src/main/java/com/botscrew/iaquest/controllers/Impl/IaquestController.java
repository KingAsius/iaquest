package com.botscrew.iaquest.controllers.Impl;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.botscrew.iaquest.api.ApiContainer;
import com.botscrew.iaquest.controllers.MainController;
import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;
import com.botscrew.iaquest.model.NotificationMessage;
import com.botscrew.iaquest.model.MeetingConfirmation;

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
	@Scheduled(fixedDelay = 300)
	public void sendNotifications() {
		LocalDateTime now = LocalDateTime.now();
		for (NotificationMessage notif : notifications) {
			if (notif.getTimeOfNotification().isAfter(now)) {
				apiContainer.sendMessage(notif);
			}
		}
	}

}
