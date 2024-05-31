package com.example.springdemo.repository;

import com.example.springdemo.entity.StudentDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface StudentRepo  extends JpaRepository<StudentDetails, UUID> {
//    String query="Select * from student where name=?";
//    @Query(value = "select s from StudentDetails s where name=:name")
    StudentDetails findByName(String name);
//    @Query("UPDATE User u SET u.email = :email WHERE u.name = :name")
//    int updateUserEmailByName(@Param("email") String email, @Param("name") String name);

}
