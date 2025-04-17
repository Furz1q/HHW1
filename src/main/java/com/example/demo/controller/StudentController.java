package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalStudents() {
        return ResponseEntity.ok(studentService.getTotalStudents());
    }

    @GetMapping("/last-five")
    public ResponseEntity<List<Student>> getLastFiveStudents() {
        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }
    @GetMapping("/students/names-starting-with-a")
    public List<String> getStudentNamesStartingWithA() {
        return studentService.getStudentsWithAName();
    }
    @GetMapping("/students/average-age")
    public double getAverageAge() {
        return studentService.getAverageAgeFromAll();
    }
}
