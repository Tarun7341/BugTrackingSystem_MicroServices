package com.user.demo.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserRequest {

    
    private Integer id;

    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name cannot be longer than 50 characters")
    private String firstname;

    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name cannot be longer than 50 characters")
    private String lastname;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[1-9]\\d{10}$", message = "Phone number should be valid")
    private String phoneNumber;

    @NotBlank(message = "Role is mandatory")
    private String role;

}
