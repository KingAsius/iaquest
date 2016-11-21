package com.botscrew.iaquest.model;

public class Meeting {

	private String channel_id;
	private String requester_id;
	private String confirmer_id;
	private String theme;
	private String action_value;

	public Meeting(MeetingRequest request, MeetingConfirmation confirmation) {
		channel_id = request.getChannel_id();
		requester_id = request.getUser_id();
		confirmer_id = confirmation.getUser_id();
		theme = request.getText();
		action_value = confirmation.getAction_value();
	}

	public String getAction_value() {
		return action_value;
	}

	public void setAction_value(String action_value) {
		this.action_value = action_value;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.requester_id = channel_id;
	}

	public String getRequester_id() {
		return requester_id;
	}

	public void setRequester_id(String requester_id) {
		this.requester_id = requester_id;
	}

	public String getConfirmer_id() {
		return confirmer_id;
	}

	public void setConfirmer_id(String confirmer_id) {
		this.confirmer_id = confirmer_id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}
