package com.example.demo.customer;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Customer {

	@Id
	@SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence")

	private Long id;
	private String name;
	private String email;
	LocalDate dob;
	@Transient
	public int age;

	// Simple constructor
	public Customer() {
	}

	// Constructor with all attributes
	public Customer(Long id, String name, String email, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	// Constructor with all attributes, except id
	public Customer(String name, String email, LocalDate dob) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	// Implementing GET
	// Calculating the customer's age
	public int getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	public void setAge(int age) {
		this.age = age;
	}

	// ToString method
	@Override
	public String toString() {
		return "Customer [id=" + id + ","
				+ "name=" + name + ","
				+ "email=" + email + ","
				+ "dob=" + dob + ","
				+ "age=" + age + "]";
	}
}

