package com.botscrew.iaquest.model;

public class MeetingConfirmation {

	private String channel_id;
	private String user_id;
	private String callback_id;
	private String action_value;

	public MeetingConfirmation() {

	}

	public MeetingConfirmation(String channel_id, String user_id, String callback_id, String action_name) {
		this.channel_id = channel_id;
		this.user_id = user_id;
		this.callback_id = callback_id;
		this.action_value = action_name;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCallback_id() {
		return callback_id;
	}

	public void setCallback_id(String callback_id) {
		this.callback_id = callback_id;
	}

	public String getAction_value() {
		return action_value;
	}

	public void setAction_value(String action_name) {
		this.action_value = action_name;
	}

}
