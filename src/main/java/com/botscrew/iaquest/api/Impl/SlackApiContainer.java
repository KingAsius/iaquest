package com.botscrew.iaquest.api.Impl;

import org.springframework.stereotype.Component;

import com.botscrew.iaquest.api.ApiContainer;
import com.botscrew.iaquest.model.Command;
import com.botscrew.iaquest.model.Message;
import com.botscrew.iaquest.model.Postback;
import com.ullink.slack.simpleslackapi.SlackSession;

@Component
public class SlackApiContainer implements ApiContainer {

	SlackSession session;

	@Override
	public void sendMessage(Message message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receiveCommand(Command command) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receivePostback(Postback postback) {
		// TODO Auto-generated method stub

	}

}
