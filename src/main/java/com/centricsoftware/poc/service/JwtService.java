package com.centricsoftware.poc.service;


import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

	@Value("${poc.security.jwt.time-to-live-second:900000}")
	private int jwtLifetime;

	@Autowired
	private JwtEncoder jwtEncoder;

	@Autowired
	private JwtDecoder jwtDecoder;

	public Jwt verifyTokenAndGet(String bearerAccessToken) {
		return jwtDecoder.decode(bearerAccessToken);
	}

	public String buildToken(UserDetails userDetails) {
		Instant now = Instant.now();

		String scope = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));

		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now)
				.expiresAt(now.plusSeconds(jwtLifetime)).subject(userDetails.getUsername()).claim("scope", scope)
				.build();

		return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	public int getJwtLifetime() {
		return jwtLifetime;
	}

}
