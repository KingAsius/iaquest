package com.botscrew.iaquest.controllers.Impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.botscrew.iaquest.api.ApiContainer;
import com.botscrew.iaquest.controllers.MainController;
import com.botscrew.iaquest.model.Action;
import com.botscrew.iaquest.model.Attachment;
import com.botscrew.iaquest.model.Meeting;
import com.botscrew.iaquest.model.MeetingConfirmation;
import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;
import com.botscrew.iaquest.tools.MeetingTimeBuilder;

@Controller
public class IaquestController implements MainController {

	@Autowired
	private ApiContainer apiContainer;

	private Map<String, MeetingRequest> meetingsArrangements = new HashMap<String, MeetingRequest>();

	@Autowired
	private MeetingTimeBuilder timeBuilder;

	@Override
	public Message requestMeeting(MeetingRequest request) {
		if (!meetingsArrangements.containsKey(request.getChannel_id())) {
			meetingsArrangements.put(request.getChannel_id(), request);
			return buildMeetingOfferMessage(request);
		} else {
			return buildChannelAlreadyHasMeetingOfferExcuse(request);
		}
	}

	@Override
	public Message confirmMeeting(MeetingConfirmation confirmation) {
		MeetingRequest request = meetingsArrangements.get(confirmation.getChannel_id());
		if (request != null) {
			Meeting meeting = new Meeting(request, confirmation);
			if (meeting.getAction_value().equalsIgnoreCase("cancel")) {
				meetingsArrangements.remove(meeting.getChannel_id());
				return new Message("Meeting was canceled");
			}
			if (!confirmation.getUser_id().equals(request.getUser_id())) {
				if (!meeting.getAction_value().equalsIgnoreCase("now")) {
					apiContainer.scheduleMeeting(meeting);
				}
				meetingsArrangements.remove(meeting.getChannel_id());
				return buildMeetingConfirmationMessage(meeting);
			} else {
				return buildMeetingOfferMessage(request, buildSelfConfirmationExcuse().getText());
			}
		} else {
			return buildMeetingRequestExpiredExcuse();
		}
	}

	private Message buildMeetingOfferMessage(MeetingRequest request) {
		Message answer = new Message();
		answer.setText("Please, schedule new meeting. Theme :" + request.getText());
		Attachment attachment = new Attachment();
		attachment.setText("Choose time: ");
		attachment.setCallback_id("time_choosing");
		attachment.setFallback("You are unable to choose time.");
		attachment.setAttachment_type("default");
		int i = 0;
		for (String timeValue : timeBuilder.suggestNewMeetingTime(LocalTime.now(), 15)) {
			Action button = new Action();
			button.setName("button_" + i);
			button.setText(timeValue);
			button.setType("button");
			button.setValue(timeValue);
			attachment.getActions().add(button);
		}
		answer.getAttachments().add(attachment);
		Attachment cancelAttachment = new Attachment();
		cancelAttachment.setCallback_id("time_choosing");
		cancelAttachment.setFallback("You are unable to choose time.");
		cancelAttachment.setAttachment_type("default");

		Action button = new Action();
		button.setName("button_cancel");
		button.setText("Cancel");
		button.setType("button");
		button.setValue("Cancel");
		cancelAttachment.getActions().add(button);

		answer.getAttachments().add(cancelAttachment);
		return answer;
	}

	private Message buildMeetingOfferMessage(MeetingRequest request, String text) {
		Message message = buildMeetingOfferMessage(request);
		message.setText(text);
		return message;
	}

	private Message buildMeetingConfirmationMessage(Meeting meeting) {
		LocalTime meetingTime;
		String meetingConfirmationMessageText;
		try {
			meetingTime = LocalTime.parse(meeting.getAction_value());
			meetingConfirmationMessageText = String.format("Meeting of %s is scheduled at %s. I will notify you just before it begins ;)", meeting.getTheme(),
					meetingTime.format(timeBuilder.getTimeFormatter()));
		} catch (DateTimeParseException exc) {
			meetingTime = LocalTime.now();
			meetingConfirmationMessageText = String.format("Meeting of %s is scheduled to now.", meeting.getTheme());

		}
		Message answer = new Message();
		answer.setText(meetingConfirmationMessageText);
		return answer;
	}

	private Message buildChannelAlreadyHasMeetingOfferExcuse(MeetingRequest meetingRequest) {
		return new Message("Sorry I can not make new meeting request in channel, where previuos was not confirmed.");
	}

	private Message buildSelfConfirmationExcuse() {
		return new Message("Opps, you can not schedule your own request.");
	}

	private Message buildMeetingRequestExpiredExcuse() {
		return new Message("This meeting request has expired.");
	}

}
