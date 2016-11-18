package com.botscrew.iaquest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vladislav on 11/18/2016.
 */
public class Confirm {

    private String title;
    private String text;
    @JsonProperty("ok_text")
    private String okText;
    @JsonProperty("dismiss_text")
    private String dismissText;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOkText() {
        return okText;
    }

    public void setOkText(String okText) {
        this.okText = okText;
    }

    public String getDismissText() {
        return dismissText;
    }

    public void setDismissText(String dismissText) {
        this.dismissText = dismissText;
    }
}
