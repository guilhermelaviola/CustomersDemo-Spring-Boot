package com.example.demo.customer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
	
	// 	Saving new customers on the system
	@Bean
	CommandLineRunner commandLineRunner (CustomerRepository repository) {
		return args -> {
			Customer nelson = new Customer(
					"Nelson Addams",
					"nelsonaddams1995@gmail.com",
					LocalDate.of(1995, 04, 5)
					);
			Customer gina = new Customer(
					"Gina Rovella",
					"gina.rovella02@gmail.com",
					LocalDate.of(2002, 02, 8)
					);
			repository.saveAll(List.of(nelson, gina));
		};
	}
}
