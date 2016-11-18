package com.botscrew.iaquest.api.Impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.botscrew.iaquest.api.ApiContainer;
import com.botscrew.iaquest.controllers.MainController;
import com.botscrew.iaquest.model.MeetingConfirmation;
import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;
import com.ullink.slack.simpleslackapi.SlackSession;

@Component
public class SlackApiContainer implements ApiContainer {

	private SlackSession session;

	@Autowired
	private MainController controller;

	@Override
	public void sendMessage(Message message) {

	}

	@Override
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void receiveCommand(MeetingRequest meetingRequest) {
		if (meetingRequest.getCommand().equals("iaquest")) {
			controller.offerNewMeeting(meetingRequest);
		}
	}

	@Override
	@RequestMapping(value = "/slack/confirmation", method = RequestMethod.POST)
	public void receivePostback(JSONObject postbackJson) {
		MeetingConfirmation meetingConfirmation = new MeetingConfirmation();

		meetingConfirmation.setChannelId(postbackJson.getJSONObject("channel").getString("id"));
		meetingConfirmation.setUserId(postbackJson.getJSONObject("user").getString("id"));
		meetingConfirmation.setCallbackId(postbackJson.getString("callback_id"));
		meetingConfirmation.setAction(postbackJson.getJSONArray("actions").getJSONObject(0).getString("name"));

	}

}
