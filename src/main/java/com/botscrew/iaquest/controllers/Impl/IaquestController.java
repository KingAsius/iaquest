package com.botscrew.iaquest.controllers.Impl;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.botscrew.iaquest.api.ApiContainer;
import com.botscrew.iaquest.controllers.MainController;
import com.botscrew.iaquest.model.Attachment;
import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;
import com.botscrew.iaquest.model.NotificationMessage;
import com.botscrew.iaquest.model.meetingconfirmation.MeetingConfirmation;

public class IaquestController implements MainController {

	@Autowired
	private ApiContainer apiContainer;

	private LinkedList<NotificationMessage> notifications;

	@Override
	public Message offerNewMeeting(MeetingRequest meetingRequest) {
		Message answer = new Message();
		answer.setText("Please, schedule new meeting. Theme :" + meetingRequest.getText());
		Attachment e = new Attachment();
		e.set
		answer.getAttachments().add(e)
		return answer;
	}

	@Override
	public Message scheduleNewMeeting(MeetingConfirmation meetingConfirmation) {
		NotificationMessage notif = new NotificationMessage();
		notif.setRecepientId(recepientId);
		notif.setText("Your meeting of " + theme + " is about to begin");
		notif.setTimeOfNotification(timeOfNotification);
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
