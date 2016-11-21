package com.botscrew.iaquest.api;

import com.botscrew.iaquest.model.Meeting;
import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;

public interface ApiContainer {

	public Message receiveCommand(MeetingRequest meetingRequest);

	public Message receivePostback(String meetingConfirmation);

	public void receiveTeamInstallationToken(String code);

	public void scheduleMeeting(Meeting meeting);

}
