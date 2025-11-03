package com.centricsoftware.poc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);

	Customer findById(long id);

	@Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Customer c WHERE c.first_name = ?1", nativeQuery = true)
	boolean existsByFirstNameVulnerable(String firstName);
	
	@Query(value = "SELECT * FROM user WHERE last_name = '" + "?1" + "'", nativeQuery = true)
	List<Customer> findUsingLastName(String lastName);
	
	

}
