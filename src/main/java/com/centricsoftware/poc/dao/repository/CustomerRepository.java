package com.centricsoftware.poc.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.centricsoftware.poc.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findById(long id);
	
	List<Customer> findByLastName(String lastName);

	@Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM Customer c WHERE c.first_name = ?1", nativeQuery = true)
	boolean existsByFirstNameVulnerable(String firstName);
	
	@Query(value = "SELECT * FROM CUSTOMER WHERE last_name = '" + "?lastName" + "'", nativeQuery = true) //2 --> parameter is bounded to prepared statements
	List<Customer> findUsingLastName2(String lastName);
	
	@Query(value = "SELECT * FROM CUSTOMER WHERE last_name = ?1", nativeQuery = true) // and other variations
	List<Customer> findUsingLastName3(String lastName);
	
	@Query(value = "SELECT * FROM customer WHERE last_name = '" + "lastName" + "'", nativeQuery = true)
	List<Customer> findUsingLastName5(String lastName);
	

}
