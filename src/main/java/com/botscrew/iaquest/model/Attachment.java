package com.botscrew.iaquest.model;

import java.util.List;

/**
 * Created by Vladislav on 11/18/2016.
 */
public class Attachment {

	private String text;
	private String fallback;
	private String callBackId;
	private List<Button> buttons;

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

	public String getCallBackId() {
		return callBackId;
	}

	public void setCallBackId(String callBackId) {
		this.callBackId = callBackId;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	public void addButton(Button button) {
		buttons.add(button);

	}

}
