package com.user.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;




@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
public class BugTrackerSpringRestApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(BugTrackerSpringRestApplication.class, args);
	}
	
	

}
