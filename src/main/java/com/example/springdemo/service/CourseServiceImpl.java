package com.example.springdemo.service;

import com.example.springdemo.entity.Course;
import com.example.springdemo.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseServiceInterface {
    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<Course> getCourseByName(String name) {
        return courseRepo.findByNameContaining(name);
    }

    @Override
    public List<Course> addCourse(List<Course> courses) {
        return courseRepo.saveAll(courses);
    }
}
