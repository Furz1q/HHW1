package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public long getTotalStudents() {
        return studentRepository.countAllStudents();
    }

    public double getAverageAge() {
        return studentRepository.findAverageAge();
    }

    public List<Student> getLastFiveStudents() {
        return studentRepository.findLastFiveStudents();
    }
}
