package com.botscrew.iaquest.api.Impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
public class SlackApiContainer implements ApiContainer {

	@Autowired
	private MainController controller;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DateTimeFormatter timeFormatter;

	@Value("${slack.client_id}")
	private String client_id;
	@Value("${slack.client_secret}")
	private String client_secret;
	@Value("${slack.team_name}")
	private String theOnlyTeamName;
	@Value("${slack.access_token}")
	private String accessToken;

	private SlackCredentials credentials;

	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void receiveTeamInstallationToken(@RequestParam String code) {
			SlackCredentials c = restTemplate.postForObject(
					"https://slack.com/api/oauth.access?client_id=" + client_id + "&client_secret=" + client_secret + "&code=" + code, null, SlackCredentials.class);
			if (c.getTeam_name().equals(theOnlyTeamName)) {
				credentials = c;
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
		try {
			LocalTime time =  LocalTime.parse(meeting.getAction_value());
			time = time.minusMinutes(5);

		String s = String.format("https://slack.com/api/reminders.add?token=%s&text=%s&time=%s&user=",
				accessToken, meeting.getTheme(), time.format(timeFormatter));
		restTemplate.getForObject(s + meeting.getConfirmer_id(), String.class);
		restTemplate.getForObject(s + meeting.getRequester_id(), String.class);
		}
		catch (NumberFormatException e) {
			System.err.println("Illegal type of action_value");
		}
	}

}
