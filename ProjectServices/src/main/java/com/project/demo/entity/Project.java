package com.project.demo.entity;

import java.util.List;

import com.project.demo.dto.Ticket;

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
public class Project {

	@Id
	private Integer id;
	private String name;
	private String description;
	private Integer userId;
	transient private List<Ticket> tickets;

}
