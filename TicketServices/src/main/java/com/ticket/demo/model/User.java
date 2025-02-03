package com.ticket.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {

	
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNumber;
	private String role;


}
