package com.botscrew.iaquest.controllers;

import com.botscrew.iaquest.model.MeetingConfirmation;
import com.botscrew.iaquest.model.MeetingRequest;
import com.botscrew.iaquest.model.Message;

public interface MainController {

	public Message requestMeeting(MeetingRequest meetingRequest);

	public Message confirmMeeting(MeetingConfirmation meetingConfirmation);

}
