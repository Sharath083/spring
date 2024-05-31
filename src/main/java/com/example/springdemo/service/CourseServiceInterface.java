package com.example.springdemo.service;

import com.example.springdemo.entity.Course;

import java.util.List;

public interface CourseServiceInterface {
    public List<Course> getCourseByName(String name);
    public List<Course> addCourse(List<Course> courses);

}
