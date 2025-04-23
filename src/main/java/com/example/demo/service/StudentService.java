package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public long getTotalStudents() {
        logger.info("Was invoked method for getting total students");
        return studentRepository.countAllStudents();
    }

    public double getAverageAge() {
        logger.info("Was invoked method for getting average age");
        return studentRepository.findAverageAge();
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Was invoked method for getting last five students");
        return studentRepository.findLastFiveStudents();
    }

    public List<String> getStudentsWithAName() {
        logger.info("Was invoked method for getting students with name starting with A");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name != null && name.toUpperCase().startsWith("А"))
                .map(String::toUpperCase)
                .sorted()
                .toList();
    }

    public double getAverageAgeFromAll() {
        logger.info("Was invoked method for getting average age from all students");
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    public List<Student> getAllStudents() {
        logger.info("Was invoked method to get all students");
        return studentRepository.findAll();
    }
}
