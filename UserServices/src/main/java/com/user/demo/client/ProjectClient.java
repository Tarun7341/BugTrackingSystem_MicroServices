package com.user.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.demo.model.Project;
//@FeignClient(url="http://localhost:9091", value = "Project-Client")
@FeignClient(name="PROJECT-SERVICE")
public interface ProjectClient {
	
	// Endpoint to get projects of a specific user by user ID
	@GetMapping("/api/projects/getProjectsByUserId/{id}")
	List<Project> getProjectsOfUsers(@PathVariable Integer id);
	

}
