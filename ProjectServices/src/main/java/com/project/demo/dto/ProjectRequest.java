package com.project.demo.dto;


import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectRequest {

    
    @Min(value = 1, message = "ID must be greater than or equal to 1")
    private Integer id;
    
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
    @NotNull(message = "Description cannot be null")
    @Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters")
    private String description;
    
    //@NotNull(message = "User ID cannot be null")
   // @Min(value = 1, message = "User ID must be greater than or equal to 1")
    private List<Integer> userId;
    
 
}
