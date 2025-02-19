package com.ticket.demo.entity;

//import org.apache.catalina.realm.JNDIRealm.User;

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


}
