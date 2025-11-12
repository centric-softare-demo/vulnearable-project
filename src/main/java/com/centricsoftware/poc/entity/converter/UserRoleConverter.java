package com.centricsoftware.poc.entity.converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import com.centricsoftware.poc.entity.UserRole;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserRoleConverter implements AttributeConverter<Set<UserRole>, String> {

	private static final String CONVERTER_DELIMITER = ",";

	@Override
	public String convertToDatabaseColumn(Set<UserRole> userRoles) {
		return userRoles.stream().map(userRole -> userRole.name()).collect(Collectors.joining(CONVERTER_DELIMITER));
	}

	@Override
	public Set<UserRole> convertToEntityAttribute(String joinedRolesFromDb) {
		return Arrays.stream(joinedRolesFromDb.split(CONVERTER_DELIMITER)).map(UserRole::valueOf)
				.collect(Collectors.toSet());
	}

}
