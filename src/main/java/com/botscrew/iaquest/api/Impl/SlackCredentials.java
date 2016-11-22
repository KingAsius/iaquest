package com.botscrew.iaquest.api.impl;

public class SlackCredentials {

	private boolean ok;
	private String access_token;
	private String scope;
	private String user_id;
	private String team_name;
	private String team_id;

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	@Override
	public String toString() {
		return "SlackCredentials{" +
				"ok=" + ok +
				", access_token='" + access_token + '\'' +
				", scope='" + scope + '\'' +
				", user_id='" + user_id + '\'' +
				", team_name='" + team_name + '\'' +
				", team_id='" + team_id + '\'' +
				'}';
	}
}
