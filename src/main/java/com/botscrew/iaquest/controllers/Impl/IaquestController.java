package com.botscrew.iaquest.controllers.Impl;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;

import com.botscrew.iaquest.api.ApiContainer;
import com.botscrew.iaquest.controllers.MainController;
import com.botscrew.iaquest.model.Command;
import com.botscrew.iaquest.model.Message;
import com.botscrew.iaquest.model.NotificationMessage;
import com.botscrew.iaquest.model.Postback;

public class IaquestController implements MainController {

	@Autowired
	private ApiContainer apiContainer;

	private LinkedList<NotificationMessage> reminders;

	@Override
	public Message commandReceived(Command command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message postbackReceived(Postback postback) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendNotifications() {
		// TODO Auto-generated method stub

	}

}
