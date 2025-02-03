package com.user.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
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
	private Integer id;
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNumber;
	private String role;

	transient private List<Ticket> tickets;
	
	transient private List<Project> projects;

}
