package com.botscrew.iaquest.api.impl;

import com.botscrew.iaquest.tools.MeetingTimeBuilder;
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

import javax.annotation.PostConstruct;
import java.io.*;
import java.time.LocalTime;

@RestController
public class SlackApiContainer implements ApiContainer {

	@Autowired
	private MainController controller;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MeetingTimeBuilder meetingTimeBuilder;

	@Value("${slack.client_id}")
	private String client_id;
	@Value("${slack.client_secret}")
	private String client_secret;
	@Value("${slack.team_name}")
	private String theOnlyTeamName;

	private SlackCredentials credentials;

	@Override
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void receiveTeamInstallationToken(@RequestParam String code) {
			SlackCredentials c = restTemplate.postForObject(
					"https://slack.com/api/oauth.access?client_id=" + client_id + "&client_secret=" + client_secret + "&code=" + code, null, SlackCredentials.class);
			if (c.getTeam_name().equals(theOnlyTeamName)) {
				credentials = c;
				try {
					String s = File.separator;
					File file = new File(System.getProperty("user.dir") + s + "src" + "main" + s + "resources" + s + "token.txt");
					if (file.exists()) {
						file.delete();
					}
					file.createNewFile();
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					writer.write(c.getAccess_token());
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
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
		try {
			LocalTime time =  LocalTime.parse(meeting.getAction_value());
			time = time.minusMinutes(5);

		String s = String.format("https://slack.com/api/reminders.add?token=%s&text=%s&time=%s&user=",
				credentials.getAccess_token(), "You have meeting with theme \"" + meeting.getTheme() + "\" 5 minutes later", time.format(meetingTimeBuilder.getTimeFormatter()));
		restTemplate.getForObject(s + meeting.getConfirmer_id(), String.class);
		restTemplate.getForObject(s + meeting.getRequester_id(), String.class);
		}
		catch (NumberFormatException e) {
			System.err.println("Illegal type of action_value");
		}
	}

	@PostConstruct
	private void checkCredentials() {
		if (credentials == null) {
			try {
				String s = File.separator;
				BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + s + "src" + "main" + s + "resources" + s + "token.txt"));
				SlackCredentials fileCredentials = new SlackCredentials();
				fileCredentials.setAccess_token(reader.readLine());
				reader.close();
				credentials = fileCredentials;
			} catch (IOException e) {
				System.err.println("Problem with token file");
				e.printStackTrace();
			}
		}
	}

}
