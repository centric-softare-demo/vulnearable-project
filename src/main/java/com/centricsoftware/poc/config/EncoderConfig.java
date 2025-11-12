package com.centricsoftware.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EncoderConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		System.out.println("------------BCrypt----------");
		return new BCryptPasswordEncoder();
	}

}
