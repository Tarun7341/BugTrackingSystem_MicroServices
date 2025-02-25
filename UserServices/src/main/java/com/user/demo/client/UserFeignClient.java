package com.user.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.demo.entity.User;
@FeignClient(name="USER-SERVICE")
public interface UserFeignClient {
	
	 @GetMapping("/api/users/getAll")
	    List<User> getAllUsers();

	    @GetMapping("/api/users/getOne/{id}")
	    User getUserById(@PathVariable List<Integer> id);

	    @PostMapping("/api/users/addNew")
	    void addNewUser(@RequestBody User user);

	    @PutMapping("/api/users/update/{id}")
	    void updateUser(@PathVariable Integer id, @RequestBody User user);

	    @DeleteMapping("/api/users/delete/{id}")
	    void deleteUser(@PathVariable Integer id);

}
