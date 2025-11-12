package com.centricsoftware.poc.api.model;

import java.time.ZonedDateTime;

public class LoginSuccessResponse {

	private String accessToken;
	private long jwtLifetimeInSeconds;
	private ZonedDateTime accessTokenExpiresOn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getJwtLifetimeInSeconds() {
		return jwtLifetimeInSeconds;
	}

	public void setJwtLifetimeInSeconds(long jwtLifetimeInSeconds) {
		this.jwtLifetimeInSeconds = jwtLifetimeInSeconds;
	}

	public ZonedDateTime getAccessTokenExpiresOn() {
		return accessTokenExpiresOn;
	}

	public void setAccessTokenExpiresOn(ZonedDateTime accessTokenExpiresOn) {
		this.accessTokenExpiresOn = accessTokenExpiresOn;
	}

}
