package com.example.springdemo.entity;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;
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
//    @NotNull(message = "Name Should not be null")
    private String name;
//    @NotNull(message = "Gender Should not be null")
    private String gender;
//    @Min(message = "Age should be greater than 18", value = 18)
//    @Max(message = "Age should be less than than 38",value = 38)
    private int age;
//    @NotNull(message = "Name Should not be null")
//    @Name("DOB")
    private String dob;
    private String mobileNumber;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "student_course_tbl",

    joinColumns = {
            @JoinColumn(name = "sId", referencedColumnName = "id")
    },
    inverseJoinColumns = {
    @JoinColumn(name = "cId",referencedColumnName = "id")
    }
    )
    private List<Course> courses;
}
