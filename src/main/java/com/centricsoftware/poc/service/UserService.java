package com.centricsoftware.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centricsoftware.poc.api.model.UserCreateRequest;
import com.centricsoftware.poc.api.model.UserPasswordUpdateRequest;
import com.centricsoftware.poc.api.model.UserPasswordUpdateResponse;
import com.centricsoftware.poc.api.model.UserResponse;
import com.centricsoftware.poc.api.model.UserUpdateRequest;
import com.centricsoftware.poc.dao.repository.UserRepository;
import com.centricsoftware.poc.entity.User;
import com.centricsoftware.poc.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	UserRepository userRepository;

	public UserResponse createUser(UserCreateRequest request) {
		boolean doesEmailOrUsernameExist = userRepository.existsByUsernameOrEmailIgnoreCase(request.getUsername(),
				request.getEmail());

		if (doesEmailOrUsernameExist) {
			return null;
		}

		User saved = userRepository.saveAndFlush(userMapper.mapToUserEntity(request));
		return userMapper.mapToReponse(saved);
	}
	
	public UserResponse updateUser(UserUpdateRequest userUpdateRequest) {

	    User userEntity = userRepository.findById(userUpdateRequest.getId()).get();
	   
	    userEntity.setFirstName(userUpdateRequest.getFirstName());
	    userEntity.setLastName(userUpdateRequest.getLastName());

	    User updatedEntity = userRepository.saveAndFlush(userEntity);

	    return userMapper.mapToReponse(updatedEntity);
	  }
	
	public UserPasswordUpdateResponse updateUserPassword(UserPasswordUpdateRequest request) {
		return null;
		
	}

	public List<UserResponse> getAllUser() {
		return userMapper.mapToUserResponses(userRepository.findAll());
	}

}
