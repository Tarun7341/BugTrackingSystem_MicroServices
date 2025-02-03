package com.ticket.demo.model;

import java.util.List;

//import org.apache.catalina.realm.JNDIRealm.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Project {

	
	private Integer id;
	private String name;
	private String description;
	private Integer userId;
	

}
