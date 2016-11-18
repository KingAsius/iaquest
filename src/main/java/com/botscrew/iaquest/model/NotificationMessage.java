package com.botscrew.iaquest.model;

import java.time.LocalDateTime;

public class NotificationMessage extends Message {

	private LocalDateTime timeOfNotification;

	public LocalDateTime getTimeOfNotification() {
		return timeOfNotification;
	}

	public void setTimeOfNotification(LocalDateTime timeOfNotification) {
		this.timeOfNotification = timeOfNotification;
	}

}
