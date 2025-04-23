package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public long getTotalStudents() {
        return studentService.getTotalStudents();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }

    @GetMapping("/names-starting-with-a")
    public List<String> getStudentNamesStartingWithA() {
        return studentService.getStudentsWithAName();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return studentService.getAverageAgeFromAll();
    }

    @GetMapping("/print-parallel")
    public void printStudentsParallel() {
        List<Student> students = studentService.getAllStudents();

        if (students.size() < 6) {
            System.out.println("Not enough students in the list");
            return;
        }

        // Первые два — в основном потоке
        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        // Третий и четвертый — в одном потоке
        Thread thread1 = new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        });

        // Пятый и шестой — в другом потоке
        Thread thread2 = new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        });

        thread1.start();
        thread2.start();
    }

    @GetMapping("/print-synchronized")
    public void printStudentsSynchronized() {
        List<Student> students = studentService.getAllStudents();

        if (students.size() < 6) {
            System.out.println("Not enough students in the list");
            return;
        }

        // Первые два — в основном потоке
        printSync(students.get(0).getName());
        printSync(students.get(1).getName());

        // Третий и четвертый — в одном потоке
        Thread thread1 = new Thread(() -> {
            printSync(students.get(2).getName());
            printSync(students.get(3).getName());
        });

        // Пятый и шестой — в другом потоке
        Thread thread2 = new Thread(() -> {
            printSync(students.get(4).getName());
            printSync(students.get(5).getName());
        });

        thread1.start();
        thread2.start();
    }

    private synchronized void printSync(String name) {
        System.out.println(name);
    }
}
