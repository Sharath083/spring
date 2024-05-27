package com.example.springdemo.service;

import com.example.springdemo.entity.StudentDetails;
import com.example.springdemo.exception.CommonException;
import com.example.springdemo.exception.NoStudentsException;
import com.example.springdemo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new NoStudentsException("Student with Id "+id+" Does Not Exists" );
        }
        return s;
    }
}
