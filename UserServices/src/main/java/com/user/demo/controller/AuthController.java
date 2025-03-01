package com.user.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.user.demo.dto.AuthRequest;
import com.user.demo.entity.User;
import com.user.demo.repository.UserRepository;
import com.user.demo.service.UserService;
import com.user.demo.serviceImpl.JwtService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService service;
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserRepository repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")		//http://localhost:9090/auth/welcome
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/new")	//http://localhost:9090/auth/new
    public String addNewUser(@RequestBody User user) {
        return service.addNew(user);
    }



    @PostMapping("/authenticate")		//http://localhost:9090/auth/authenticate
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
        	User obj = repo.findByEmail(authRequest.getUsername()).orElse(null);
            return jwtService.generateToken(authRequest.getUsername(),obj.getRoles());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    
    @GetMapping("/getroles/{username}")		//http://localhost:9090/auth/getroles/{username}
    public String getRoles(@PathVariable String username)
    {
    	return service.getRoles(username);
    }
}
