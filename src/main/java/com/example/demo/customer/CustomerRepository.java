package com.example.demo.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// This interface is used for data access
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("SELECT c FROM Customer c WHERE c.email = 'Whatever we parse...'")
	Optional <Customer> findCustomerByEmail(String email);
}
