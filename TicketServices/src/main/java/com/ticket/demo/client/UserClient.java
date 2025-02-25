package com.ticket.demo.client;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ticket.demo.dto.User;

@FeignClient(name="USER-SERVICE")
public interface UserClient {
	
	@GetMapping("api/users/getOne/{id}")
	User getUserById(@PathVariable Integer id);

}
