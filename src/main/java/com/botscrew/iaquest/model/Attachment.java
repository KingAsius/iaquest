package com.botscrew.iaquest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vladislav on 11/18/2016.
 */
public class Attachment {

    private String text;
    private String fallback;
    @JsonProperty("callback_id")
    private String callbackId;
}
