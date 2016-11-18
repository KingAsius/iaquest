package com.botscrew.iaquest.api;

import org.json.JSONObject;

import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;

public interface ApiContainer {

	public void sendMessage(Message message);

	public void receiveCommand(MeetingRequest meetingRequest);

	public void receivePostback(JSONObject meetingConfirmation);

}
