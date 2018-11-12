package com.example.axon.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.axon.demo"})
@EntityScan(basePackages = {"com.example.axon.demo",
		"org.axonframework.eventsourcing.eventstore.jpa",
		"org.axonframework.eventhandling.saga.repository.jpa",
		"org.axonframework.eventhandling.tokenstore.jpa"})
@EnableJpaRepositories(basePackages = {"com.example.axon.demo.query"})
public class DemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
