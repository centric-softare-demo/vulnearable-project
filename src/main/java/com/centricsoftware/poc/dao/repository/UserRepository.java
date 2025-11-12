package com.centricsoftware.poc.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centricsoftware.poc.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	boolean existsByUsernameOrEmailIgnoreCase(String username, String email);

	Optional<User> findOneByUsername(String username);

}
