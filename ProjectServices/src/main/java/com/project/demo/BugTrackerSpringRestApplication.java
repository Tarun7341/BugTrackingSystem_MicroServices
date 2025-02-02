package com.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BugTrackerSpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugTrackerSpringRestApplication.class, args);
	}

}
