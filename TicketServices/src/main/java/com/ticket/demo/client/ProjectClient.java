package com.ticket.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ticket.demo.model.Project;

@FeignClient("PROJECT-SERVICE")
public interface ProjectClient {

	@GetMapping("api/projects/getOne/{id}")
	Project getProjectById(@PathVariable Integer id);
}
