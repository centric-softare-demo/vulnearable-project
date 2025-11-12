package com.centricsoftware.poc.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UserCreateRequest {

	private final String username, email, password, firstName, lastName;

	@JsonCreator
	public UserCreateRequest(String username, String email, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "UserCreateRequest [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
