package com.example.springdemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Name;
import lombok.*;


import java.util.UUID;

@Entity
@Data
@Table(name = "student")
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "Name Should not be null")
    private String name;
    @NotNull(message = "Gender Should not be null")
    private String gender;
    @Min(message = "Age should be greater than 18", value = 18)
    @Max(message = "Age should be less than than 38",value = 38)
    private int age;
    @NotNull(message = "Name Should not be null")
    @Name("DOB")
    private String dob;
    private String mobileNumber;
}
