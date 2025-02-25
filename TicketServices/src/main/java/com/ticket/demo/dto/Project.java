package com.ticket.demo.dto;

import java.util.List;




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
	private List<Integer> userId;
	

}
