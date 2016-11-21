package com.botscrew.iaquest.model;

import java.util.LinkedList;
import java.util.List;

public class Attachment {

	private String text;
	private String fallback;
	private String callback_id;
	private String attachment_type;
	private List<Action> actions = new LinkedList<Action>();

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFallback() {
		return fallback;
	}

	public void setFallback(String fallback) {
		this.fallback = fallback;
	}

	public String getCallback_id() {
		return callback_id;
	}

	public void setCallback_id(String callback_id) {
		this.callback_id = callback_id;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> buttons) {
		this.actions = buttons;
	}

	public String getAttachment_type() {
		return attachment_type;
	}

	public void setAttachment_type(String attachment_type) {
		this.attachment_type = attachment_type;
	}

}
