package com.botscrew.iaquest.model.messageconfirmation;

import com.botscrew.iaquest.model.Action;
import com.botscrew.iaquest.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MeetingConfirmation {
    private List<Action> actions = new ArrayList<>();
    private String callback_id;
    private Team team;
    private Channel channel;
    private User user;
    private String action_ts;
    private String message_ts;
    private String attachment_id;
    private String token;
    private Message message;
    private String response_url;

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public String getResponse_url() {
        return response_url;
    }

    public void setResponse_url(String response_url) {
        this.response_url = response_url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(String attachment_id) {
        this.attachment_id = attachment_id;
    }

    public String getMessage_ts() {
        return message_ts;
    }

    public void setMessage_ts(String message_ts) {
        this.message_ts = message_ts;
    }

    public String getAction_ts() {
        return action_ts;
    }

    public void setAction_ts(String action_ts) {
        this.action_ts = action_ts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getCallback_id() {
        return callback_id;
    }

    public void setCallback_id(String callback_id) {
        this.callback_id = callback_id;
    }
}
