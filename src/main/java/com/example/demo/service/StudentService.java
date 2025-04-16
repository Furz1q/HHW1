package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public long getTotalStudents() {
        logger.info("Was invoked method for getting total number of students");
        logger.debug("Calling studentRepository.countAllStudents()");
        long total = studentRepository.countAllStudents();
        logger.debug("Total students found: {}", total);
        return total;
    }

    public double getAverageAge() {
        logger.info("Was invoked method for getting average age of students");
        logger.debug("Calling studentRepository.findAverageAge()");
        double avgAge = studentRepository.findAverageAge();
        if (avgAge <= 0) {
            logger.warn("Average age is calculated as zero or less, check student data");
        }
        logger.debug("Average age calculated: {}", avgAge);
        return avgAge;
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Was invoked method for getting last five students");
        logger.debug("Calling studentRepository.findLastFiveStudents()");
        List<Student> students = studentRepository.findLastFiveStudents();
        if (students.isEmpty()) {
            logger.warn("No students found when retrieving the last five students");
        } else {
            logger.debug("Last five students retrieved: {}", students);
        }
        return students;
    }
}
