package com.centricsoftware.poc;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.inject.Inject;

@Service
public class CustomerService {

	@Inject
	private CustomerRepository customerRepository;

	public List<Customer> findByLastName(String lastName) {
		return customerRepository.findByLastName(lastName);
	}

	public Customer save(Customer customer) {
		return customerRepository.saveAndFlush(customer);
	}

	public Customer update(Customer customer) {
		boolean doesCustomerExist = customerRepository.existsById(customer.getId());
		if (doesCustomerExist) {
			return customerRepository.saveAndFlush(customer);
		}

		return null;

	}

	public Customer patchCustomerLastName(Long customerId, String lastName) {
		Optional<Customer> existingCustomer = customerRepository.findById(customerId);
		return existingCustomer.map(customer -> {
			customer.setLastName(lastName);
			return customerRepository.save(customer);
		}).orElse(null);

	}

	public Customer save(Long customerId, Customer customer) {
		if (customerRepository.existsById(customerId)) {
			return customerRepository.saveAndFlush(customer);
		}
		return null;
	}
	
	public boolean doesCustomerExistByFirstNameVulnerable(String firstName) {
	    return customerRepository.existsByFirstNameVulnerable(firstName);
	}

}
