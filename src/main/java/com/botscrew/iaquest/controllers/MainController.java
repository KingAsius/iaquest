package com.botscrew.iaquest.controllers;

import com.botscrew.iaquest.model.Command;
import com.botscrew.iaquest.model.Message;
import com.botscrew.iaquest.model.Postback;

public interface MainController {

	public Message commandReceived(Command command);

	public Message postbackReceived(Postback postback);

	public void sendNotifications();

}
