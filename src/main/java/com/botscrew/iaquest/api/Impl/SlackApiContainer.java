package com.botscrew.iaquest.api.Impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.botscrew.iaquest.api.ApiContainer;
import com.botscrew.iaquest.controllers.MainController;
import com.botscrew.iaquest.model.Meeting;
import com.botscrew.iaquest.model.MeetingConfirmation;
import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;

@RestController
public class SlackApiContainer implements ApiContainer {

	@Autowired
	private MainController controller;

	@Autowired
	private RestTemplate restTemplate;

	private String client_id = "81167029479.81229271473";
	private String client_secret = "86744ff4fb6aa355ecd54d22cc1b0fa4";
	private String theOnlyTeamName = "Asmeralt's";

	private SlackCredentials credentials;

	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void receiveTeamInstallationToken(@RequestParam String code) {
		if (credentials == null) {
			SlackCredentials c = restTemplate.postForObject(
					"https://slack.com/api/oauth.access?client_id=" + client_id + "&client_secret=" + client_secret + "&code=" + code, null, SlackCredentials.class);
			if (c.getTeam_id().equals(theOnlyTeamName)) {
				credentials = c;
			}
		}
	}

	@Override
	@RequestMapping(value = "/slashcommand", method = RequestMethod.POST)
	public Message receiveCommand(MeetingRequest meetingRequest) {
		if (meetingRequest.getCommand().equals("/iaquest")) {
			return controller.requestMeeting(meetingRequest);
		}
		return new Message("Oops, unknown command");
	}

	@Override
	@RequestMapping(value = "/buttonpostback", method = RequestMethod.POST)
	public Message receivePostback(@RequestParam String payload) {
		JSONObject postbackJson = new JSONObject(payload);
		String channel_id = postbackJson.getJSONObject("channel").getString("id");
		String user_id = postbackJson.getJSONObject("user").getString("id");
		String callback_id = postbackJson.getString("callback_id");
		String action_name = postbackJson.getJSONArray("actions").getJSONObject(0).getString("value");
		return controller.confirmMeeting(new MeetingConfirmation(channel_id, user_id, callback_id, action_name));
	}

	@Override
	public void scheduleMeeting(Meeting meeting) {
		// TODO Auto-generated method stub

	}

}
