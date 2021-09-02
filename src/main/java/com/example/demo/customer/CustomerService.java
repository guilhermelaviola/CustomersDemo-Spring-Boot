package com.example.demo.customer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	// Implementing GET
	// The customers will be returned as a list
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}

	// Implementing POST
	public void addNewCustomer(Customer customer) {
		Optional <Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());

		// Checking whether the email is already present in the database or not and throwing an exception
		// in case it's already taken
		if (customerOptional.isPresent()) {
			throw new IllegalStateException("The email is already taken!");
		}
		// Saving the new client on the database
		customerRepository.save(customer);
	}

	// Implementing DELETE
	public void deleteCustomer(Long customerId) {
		// The customer will be deleted only if it exists on the database, otherwise an exception will be thrown
		boolean exists = customerRepository.existsById(customerId);
		if (!exists) {
			throw new IllegalStateException("The customer with id " + customerId + " does't exist.");
		}
		// DeletingCustomer
		customerRepository.deleteById(customerId);
	}
	
	// Implementing PUT
	@Transactional
	public void updateCustomer(Long customerId, String name, String email) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new IllegalStateException("Customer with id " + customerId + " doesn't exist."));

		// If the new name is not null, have the length is larger than zero and is not the same that the former
		// name, then it's set as new name
		if(name != null && name.length() != 0 && !Objects.equals(customer.getName(), name)) {
			customer.setName(name);
		}

		// If the new email is not null, have the length is larger than zero, is not the same that the former
		// email or isn't already taken by another customer in the system, then it's set as new email
		if(email != null && email.length() != 0 && !Objects.equals(customer.getEmail(), email)) {
			Optional <Customer> customerOptional = customerRepository.findCustomerByEmail(email);
			if (customerOptional.isPresent()) {
				throw new IllegalStateException("The email is already taken!");
			}
			customer.setEmail(email);
		}
	}
}
