package com.centricsoftware.poc.api.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.centricsoftware.poc.entity.Customer;
import com.centricsoftware.poc.service.CustomerService;

import jakarta.inject.Inject;

@RestController
@RequestMapping("/api/v1/rest/customers")
public class CustomerRestController {

		@Inject
	private CustomerService customerService;

	@GetMapping("/vulnerable-xss")
	public String greet(@RequestParam(value = "name", required = false, defaultValue = "John Doe") String name) {
		return "{\"greetings\": \"Hello! " + name + "\"}";
	}

	@GetMapping("/search")
	public List<Customer> findAllCustomersByLastName(@RequestParam(value = "lastNameSearch") String lastNameSearch) {
		return customerService.findByLastName(lastNameSearch);
	}

	@GetMapping("/{id}/vulnerable-csrf-stored-xss")
	public Customer greet(@PathVariable Long id, @RequestParam(value = "lastNameExcess", required = false, defaultValue = "John Doe") String lastNameExcess) {
		return customerService.patchCustomerLastName(id, lastNameExcess);
	}

	@PostMapping
	public Customer addANewCustomer(@RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@PutMapping
	public Customer updateCustomer(@RequestBody Customer customer) {
		return customerService.update(customer);
	}

	@GetMapping("/{id}")
	public Customer updateCustomer2(@PathVariable Long id, @RequestParam String lastName){
		Customer c = customerService.findById(id);
		c.setLastName(lastName);
		return customerService.save(c);
	}
	
	@GetMapping("/lookup1-em")
	public List<Customer> updateCustomer3(@RequestParam(value = "lastNameA") String lastNameA){
		return customerService.findUsingLastNameIgnoreCase(lastNameA);
	}
	
	@GetMapping("/lookup2")
	public List<Customer> searchCustomer(@RequestParam(value = "lastNameB") String lastNameB){
		return customerService.findUsingLastName(lastNameB);
	}
	
	@GetMapping("/lookup3")
	public List<Customer> searchCustomerss(@RequestParam(value = "lastNameC") String lastNameC){
		return customerService.findUsingLastNameAdd(lastNameC);
	}
	
	@GetMapping("/lookup4-em-native")
	public List<Customer> somethingCustomerzz(@RequestParam(value = "lastNameD") String lastNameD){
		return customerService.doesCustomerExistByFirstName(lastNameD);
	}
	
	@GetMapping("/lookup5")
	public List<Customer> somethingzCustomerzz(@RequestParam(value = "lastNameE") String lastNameE){
		return customerService.doesCustomerExistByFirstName5(lastNameE);
	}
	
	

}
