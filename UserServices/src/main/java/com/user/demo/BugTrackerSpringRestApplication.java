package com.user.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BugTrackerSpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugTrackerSpringRestApplication.class, args);
	}

}
