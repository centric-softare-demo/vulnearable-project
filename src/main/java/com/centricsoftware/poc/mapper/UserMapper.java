package com.centricsoftware.poc.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.centricsoftware.poc.api.model.UserCreateRequest;
import com.centricsoftware.poc.api.model.UserResponse;
import com.centricsoftware.poc.entity.User;
import com.centricsoftware.poc.entity.UserRole;

@Component
public class UserMapper {

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public final User mapToUserEntity(UserCreateRequest request) {
		return mapToUserEntity(request, Set.of(UserRole.ROLE_USER), true);
	}

	public final User mapToUserEntity(UserCreateRequest request, Set<UserRole> userRoles, boolean isActive) {
		User entity = new User();
		entity.setFirstName(request.getFirstName());
		entity.setLastName(request.getLastName());
		entity.setUsername(request.getUsername());
		entity.setEmail(request.getEmail());
		entity.setRoles(userRoles);
		entity.setActive(isActive);
		entity.setPasswordHash(bCryptPasswordEncoder.encode(request.getPassword()));
		return entity;
	}

	public final UserResponse mapToReponse(User entity) {
		UserResponse response = new UserResponse();
		response.setUsername(entity.getUsername());
		response.setEmail(entity.getEmail());
		response.setFirstName(entity.getFirstName());
		response.setLastName(entity.getLastName());
		response.setRoles(entity.getRoles());
		response.setId(entity.getId());
		response.setActive(entity.isActive());
		return response;
	}

	public List<UserResponse> mapToUserResponses(List<User> userEntities) {
		return userEntities.stream().map(entity -> mapToReponse(entity)).collect(Collectors.toList());
	}

}
