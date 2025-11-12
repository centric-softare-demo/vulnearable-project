package com.centricsoftware.poc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centricsoftware.poc.dao.repository.CustomerRepository;
import com.centricsoftware.poc.entity.Customer;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Service
public class CustomerService {

	@Inject
	private CustomerRepository customerRepository;

	@Autowired
	private EntityManager em;

	public List<Customer> findByLastName(String lastName) {
		return customerRepository.findByLastName(lastName);
	}

	public Customer findById(Long id) {
		return customerRepository.findById(id).map(x -> x).orElse(null);
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

	public List<Customer> findUsingLastName(String firstName) {
		return customerRepository.findUsingLastName(firstName);
	}

	public List<Customer> findUsingLastNameIgnoreCase(String lastName) {
		String jpql = "SELECT u FROM User u WHERE u.name LIKE '%" + lastName + "%'";
		return em.createQuery(jpql, Customer.class).getResultList();
	}

	public boolean doesCustomerExistByFirstNameVulnerable(String firstName) {
		return false;
	}

}
