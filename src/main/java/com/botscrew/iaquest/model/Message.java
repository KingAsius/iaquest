package com.botscrew.iaquest.model;

public class Message {

	private String text;

	private Attachment attachment;

	private String recepientId;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public String getRecepientId() {
		return recepientId;
	}

	public void setRecepientId(String recepientId) {
		this.recepientId = recepientId;
	}

}
