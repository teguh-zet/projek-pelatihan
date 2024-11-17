package com.project.haiportal.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.haiportal.models.Student;

@Service
public class StudentService {
    private List <Student> students = new ArrayList<>();

    public List <Student> getAllStudent(){
        return students;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }
}
