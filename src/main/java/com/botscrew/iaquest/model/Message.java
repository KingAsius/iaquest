package com.botscrew.iaquest.model;

import java.util.ArrayList;
import java.util.List;

public class Message {

    private String text;
    private List<Attachment> attachments = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
