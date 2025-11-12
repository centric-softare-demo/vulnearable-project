package com.centricsoftware.poc.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

	private final String username;

	private final String password;

	@JsonCreator
	public LoginRequest(@JsonProperty(value = "username") String username, @JsonProperty(value = "password") String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
