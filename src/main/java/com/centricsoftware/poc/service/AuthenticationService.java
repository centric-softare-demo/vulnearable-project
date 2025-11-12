package com.centricsoftware.poc.service;

import static com.centricsoftware.poc.Constant.ZONE_UTC;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.centricsoftware.poc.api.model.LoginRequest;
import com.centricsoftware.poc.api.model.LoginSuccessResponse;
import com.centricsoftware.poc.dao.repository.UserRepository;
import com.centricsoftware.poc.entity.User;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	public LoginSuccessResponse authenticate(LoginRequest input) {
		Instant jwtExpiresOn = Instant.now().plusSeconds(jwtService.getJwtLifetime());

		User user = userRepository.findOneByUsername(input.getUsername()).orElseThrow();

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));
		String jwtToken = jwtService.buildToken(user);

		LoginSuccessResponse successToken = new LoginSuccessResponse();

		successToken.setAccessToken(jwtToken);
		successToken.setJwtLifetimeInSeconds(jwtService.getJwtLifetime());
		successToken.setAccessTokenExpiresOn(jwtExpiresOn.truncatedTo(ChronoUnit.SECONDS).atZone(ZONE_UTC));

		return successToken;

	}

}
