package com.eureka.demo.filter;


import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {


	public static final String[] OPEN_API_ENDPOINTS = { "/api/auth/authenticate", "/api/auth/new", "/api/auth/validate", "/eureka", "/api/users/ids-and-names","/api/projects/ids-and-names"};

	public Predicate<ServerHttpRequest> isSecured = request -> {
		String path = request.getPath().toString();
		for (String endpoint : OPEN_API_ENDPOINTS) {
			if (path.contains(endpoint)) {
				return false; // Endpoint does not require authorization
			}
		}
		return true; // Endpoint requires authorization
	};

	
	

}
