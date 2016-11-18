package com.botscrew.iaquest.controllers;

import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;
import com.botscrew.iaquest.model.meetingconfirmation.MeetingConfirmation;

public interface MainController {

	public Message offerNewMeeting(MeetingRequest command);

	public Message scheduleNewMeeting(MeetingConfirmation postback);

	public void sendNotifications();

}
