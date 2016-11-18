package com.botscrew.iaquest.api;

import com.botscrew.iaquest.model.messageconfirmation.MeetingConfirmation;
import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;

public interface ApiContainer {

	public void sendMessage(Message message);

	public void receiveCommand(MeetingRequest meetingRequest);

	public void receivePostback(MeetingConfirmation meetingConfirmation);

}
