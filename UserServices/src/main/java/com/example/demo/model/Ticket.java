package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {

	private Integer id;
	private String title;
	private String description;
	private String status;
	private String priority;
	private String type;
	private String severity;
	private String stepstoReproduce;
}
