package com.user.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.user.demo.dto.Project;
//@FeignClient(url="http://localhost:9091", value = "Project-Client")
@FeignClient(name="PROJECT-SERVICE")
public interface ProjectFeignClient {
	
	// Endpoint to get projects of a specific user by user ID
	@GetMapping("/api/projects/getProjectsByUserId/{id}")
	List<Project> getProjectsOfUsers(@PathVariable Integer id);
	
	
    @GetMapping("/api/projects/getAll")
    List<Project> getAllProjects();
    
    @GetMapping("/api/projects/getOne/{id}")
    Project getProjectById(@PathVariable Integer id);

    @PostMapping("/api/projects/addNew")
    void addProject(@RequestBody Project project);

    @PutMapping("/api/projects/update/{id}")
    void updateProject(@PathVariable Integer id, @RequestBody Project project);

    @DeleteMapping("/api/projects/delete/{id}")
    void deleteProject(@PathVariable Integer id);
    
    @PutMapping("/api/projects/removeUser/{id}/{userIdToRemove}")
    public String removeUserId(@PathVariable Integer id,@PathVariable Integer userIdToRemove);
	
	
}
