package com.botscrew.iaquest.api;

import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;
import com.botscrew.iaquest.model.MeetingConfirmation;

public interface ApiContainer {

	public void sendMessage(Message message);

	public void receiveCommand(MeetingRequest command);

	public void receivePostback(MeetingConfirmation postback);

}
