package com.centricsoftware.poc.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centricsoftware.poc.api.model.UserResponse;
import com.centricsoftware.poc.service.UserService;

@RestController
@RequestMapping("/api/v1/rest/admin/users")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<UserResponse> getAllUser(){
		return userService.getAllUser();
	}
	
	

}
