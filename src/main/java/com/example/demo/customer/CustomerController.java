package com.example.demo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/customer")
public class CustomerController {
	
	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	// Implementing GET
	@GetMapping
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	
	// Implementing POST
	// The @RequestBody is taken and mapped into the Customer
	@PostMapping
	public void registerNewCustomer (@RequestBody Customer customer) {
		customerService.addNewCustomer(customer);
	}
	
	// Implementing DELETE
	@DeleteMapping(path = "{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Long customerId) {
		customerService.deleteCustomer(customerId);
	}
	
	// Implementing PUT
	@PutMapping(path = "{customerId}")
	public void updateCustomer(@PathVariable("customerId") Long customerId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
		customerService.updateCustomer(customerId, name, email);
	}
}
