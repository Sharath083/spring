package com.example.springdemo.service;

import com.example.springdemo.entity.StudentDetails;
import com.example.springdemo.exception.ExceptionsHandler;
import com.example.springdemo.exception.CommonException;
import com.example.springdemo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepo studentRepo;
    @Override
    public StudentDetails studentRegister(StudentDetails data) {
        return studentRepo.save(data);
    }

    @Override
    public StudentDetails getStudentData(UUID id) throws CommonException {
        var s=studentRepo.findById(id).orElse(null );
        if(s==null){
            throw new CommonException("001","Student with Id "+id+" Does Not Exists" );
        }
        return s;
    }
    public StudentDetails addCourseToStudent(StudentDetails studentDetails) {
        return studentRepo.save(studentDetails);
    }
    @Override
    public StudentDetails getStudentByName(String name){
        return studentRepo.findByName(name);
    }

}
