package com.ticket.demo.dto;


import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

public class TicketRequest {
    
    private Integer id;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title cannot be longer than 100 characters")
    private String title;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "Status is mandatory")
    private String status;

    @NotBlank(message = "Priority is mandatory")
    private String priority;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotBlank(message = "Severity is mandatory")
    private String severity;

    @NotBlank(message = "Steps to Reproduce is mandatory")
    private String stepstoReproduce;

    @NotNull(message = "Project ID is mandatory")
    @Min(value = 1, message = "Project ID must be a positive integer")
    private Integer projectId;

    @NotNull(message = "User ID is mandatory")
   // @Min(value = 1, message = "User ID must be a positive integer")
    private List<Integer> userId;
}

