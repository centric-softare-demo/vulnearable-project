package com.centricsoftware.poc;

import java.util.Map;

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
	public Map<String, Boolean> checkCustomerExistenceVulnerable(@RequestParam(value = "firstName") String firstName) {
	    boolean exists = customerService.doesCustomerExistByFirstNameVulnerable(firstName);
	    return Map.of("customerExists", exists);
	}
	
	

}
