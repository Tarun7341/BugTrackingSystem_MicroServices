package com.user.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Getter
@Setter

public class Project {


	private Integer id;
	private String name;
	private String description;
	private Integer userId;
	

}
