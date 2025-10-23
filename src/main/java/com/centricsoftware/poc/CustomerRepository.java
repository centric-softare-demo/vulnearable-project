package com.centricsoftware.poc;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);

    @Query(value = "SELECT * FROM CUSTOMER WHERE FIRST_NAME=?1", nativeQuery = true)
    List<Customer> findByName(String name);

}
