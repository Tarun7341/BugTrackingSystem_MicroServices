package com.user.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.demo.model.Project;
//@FeignClient(url="http://localhost:9091", value = "Project-Client")
@FeignClient(name="PROJECT-SERVICE")
public interface ProjectClient {
	
	@GetMapping("/api/projects/getProjectsByUserId/{userId}")
	List<Project> getProjectsOfUsers(@PathVariable("userId") Integer Id);

}
