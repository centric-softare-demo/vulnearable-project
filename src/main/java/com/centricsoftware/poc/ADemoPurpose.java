package com.centricsoftware.poc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/demo-purpose")
public class ADemoPurpose {
	
	@Autowired
    private CustomerService customerService;
	
	@GetMapping("/vulnerable-blind-sqli-idor")
	public List<Customer> checkCustomerExistenceVulnerable(@RequestParam(value = "firstName") String firstName) {
	    return customerService.doesCustomerExistByFirstName(firstName);
	    
	}
	
	

}
