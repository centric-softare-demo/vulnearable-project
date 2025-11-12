package com.centricsoftware.poc.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.centricsoftware.poc.api.model.LoginRequest;
import com.centricsoftware.poc.api.model.LoginSuccessResponse;
import com.centricsoftware.poc.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/rest/login")
public class LoginRestController {

	@Autowired
	AuthenticationService authService;

	@GetMapping
	public LoginSuccessResponse login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		LoginRequest loginRequest = new LoginRequest(username, password);
		return authService.authenticate(loginRequest);
	}

	@PutMapping
	public LoginSuccessResponse loginByAnothers(@RequestBody LoginRequest loginRequest) {
		return authService.authenticate(loginRequest);
	}

}
