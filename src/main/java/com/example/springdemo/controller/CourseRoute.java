package com.example.springdemo.controller;

import com.example.springdemo.entity.Course;
import com.example.springdemo.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseRoute {
    @Autowired
    private CourseServiceImpl courseService;

    @PostMapping("/add")
    public List<Course> setCourseService(List<Course> courseList) {
        return courseService.addCourse(courseList);
    }
}
