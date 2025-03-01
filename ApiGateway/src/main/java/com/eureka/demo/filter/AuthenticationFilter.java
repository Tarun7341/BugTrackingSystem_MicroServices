package com.eureka.demo.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.eureka.demo.util.JwtUtil;
import com.google.common.net.HttpHeaders;

import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil util;

    public static class Config {
    }

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return handleUnauthorized(exchange.getResponse(), "Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    String role = util.extractRolesFromToken(authHeader);
                    String requestedPath = exchange.getRequest().getPath().toString();
                    String method = exchange.getRequest().getMethod().name();

                    if (!isAuthorized(role, requestedPath, method)) {
                        return handleUnauthorized(exchange.getResponse(), "Unauthorized access");
                    }

                } catch (Exception e) {
                    return handleUnauthorized(exchange.getResponse(), "Invalid token");
                }
            }
            return chain.filter(exchange);
        };
    }

    private boolean isAuthorized(String role, String path, String method) {
        switch (role) {
        case "ROLE_ADMIN":
            return (method.equals("PUT") && (path.matches("/api/users/update/\\d+") || 
                                             path.matches("/api/projects/update/\\d+") || 
                                             path.matches("/api/tickets/update/\\d+") || 
                                             path.matches("/api/projects/removeUser/\\d+/\\d+") || 
                                             path.matches("/api/tickets/removeUser/\\d+/\\d+"))) ||
                   (method.equals("POST") && path.equals("/api/projects/addNew")) ||
                   (method.equals("DELETE") && (path.matches("/api/users/delete/\\d+") || 
                                                path.matches("/api/projects/delete/\\d+"))) ||
                   (method.equals("GET") && (path.equals("/api/projects/getAll") || 
                		   					(path.equals("/api/users/getAll"))||
                                             path.equals("/api/tickets/getAll")));


            case "ROLE_DEVELOPER":
                return (method.equals("GET") && (path.matches("/api/projects/getProjectsByUserId/\\d+") || 
                                                 path.matches("/api/tickets/users/\\d+"))) ||
                		(method.equals("DELETE") && path.matches("/api/users/delete/\\d+")) ||
                       (method.equals("PUT") && path.matches("/api/tickets/update/\\d+")||
                    		   path.matches("/api/users/update/\\d+") );

            case "ROLE_TESTER":
                return (method.equals("POST") && path.equals("/api/tickets/addNew")) ||
                       (method.equals("PUT") && path.matches("/api/tickets/update/\\d+") ||
                    		   path.matches("/api/users/update/\\d+"))|| 
                       (method.equals("DELETE") && path.matches("/api/tickets/delete/\\d+")||
                    		   path.matches("/api/users/delete/\\d+")) ||
                       (method.equals("GET") && path.matches("/api/tickets/getOne/\\d+"));

            default:
                return false;
        }
    }

    private Mono<Void> handleUnauthorized(ServerHttpResponse response, String message) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }
}
