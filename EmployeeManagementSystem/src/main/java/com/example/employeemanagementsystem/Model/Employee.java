package com.example.employeemanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Employee {
    @NotNull(message = "id cannot be empty")
    @Size(min = 2,message = "id must be at least 2 characters")
    private String id;
    @NotNull(message = "name cannot be empty")
    @Size(min = 4 ,message ="name must be at least 4 characters" )
    @Pattern(regexp = "^[a-zA-Z]*$",message = "name must only contain letters")
    private String name;
    @Email(message = "please enter a valid email")
    private String email;
    @Pattern(regexp = "^05\\d{8}$", message = "phone number must start with 05 and 10 digits")
    private String phoneNumber;
    @NotNull(message = "age cannot be empty")
    @Digits(integer = 2,fraction = 0,message = "age must be a number")
    @Min(value = 25,message = "age must be more than 25")
    private int age;
    @NotNull(message = "position cannot be null")
    @Pattern(regexp = "supervisor|coordinator",message = "position must be either supervisor or coordinator")
    private String position;
    @AssertFalse(message = "default value of onLeave must be set to false")
    private boolean onLeave;
    @NotNull(message = "date cannot be null")
    @PastOrPresent(message = "hire date cannot be in the future")
    private LocalDateTime hireDate;
    @NotNull(message = "annual leave cannot be empty")
    @Positive(message = "annual leave must be a positive number")
    private int annualLeave;


}
