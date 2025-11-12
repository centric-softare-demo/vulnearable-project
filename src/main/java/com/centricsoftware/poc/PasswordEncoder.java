package com.centricsoftware.poc;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public static void main(String[] args) {
		String myPassword = "LVZu5G2yDfn8Tkx";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode(myPassword));
	}

}
