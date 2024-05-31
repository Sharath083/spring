package com.example.springdemo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class UserReq {
    @NotNull(message = "Name Should not be empty")
    private String name;
    @Min(value = 15,message = "Age should be greater than 15")
    @Max(value = 88,message = "Age Should be less than 89")
    private int age;
    @Email(message = "Invalid Email Format")
    private String email;
    @NotNull(message = "Mobile number should not be blank")
    @Pattern(regexp = "^\\d{10}$",message = "Invalid Mobile Number")
    private String mobileNumber;
    @NotNull(message = "Password should not be blank")
    @Size(min = 8,message = "Password should be atleast 8 to 15 characters",max = 15)
    private String password;
}
