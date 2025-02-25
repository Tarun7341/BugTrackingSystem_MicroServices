package com.user.demo.entity;

import java.util.List;

import com.user.demo.dto.Project;
import com.user.demo.dto.Ticket;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private Roles role;
	private String password;
	
	
	
  
	

	transient private List<Ticket> tickets;
	
	transient private List<Project> projects;

}
