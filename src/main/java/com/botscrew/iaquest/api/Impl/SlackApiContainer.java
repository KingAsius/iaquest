package com.botscrew.iaquest.api.Impl;

import org.springframework.stereotype.Component;

import com.botscrew.iaquest.api.ApiContainer;
import com.botscrew.iaquest.model.MeetingConfirmation;
import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;
import com.ullink.slack.simpleslackapi.SlackSession;

@Component
public class SlackApiContainer implements ApiContainer {

	private SlackSession session;

	@Override
	public void sendMessage(Message message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void receiveCommand(MeetingRequest meetingRequest) {
		// TODO Auto-generated method stub
	}

	@Override
	public void receivePostback(MeetingConfirmation meetingConfirmation) {
		// TODO Auto-generated method stub
	}

}
