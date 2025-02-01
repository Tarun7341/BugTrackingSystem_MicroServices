package com.example.demo.model;

//import org.apache.catalina.realm.JNDIRealm.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ticket {
	@Id
	private Integer id;
	private String title;
	private String description;
	private String status;
	private String priority;
	private String type;
	private String severity;
	private String stepstoReproduce;

	private Integer projectId;

	private Integer userId;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="project_id")
//	private Project project;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="assigned_to")
//	private User assignee;

}
