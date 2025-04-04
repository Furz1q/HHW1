package com.example.demo;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = "http://localhost:" + port + "/students";
        studentRepository.deleteAll();
    }

    @Test
    public void testGetTotalStudents() {
        studentRepository.save(new Student(null, "Harry Potter", 17, null));
        studentRepository.save(new Student(null, "Hermione Granger", 18, null));

        ResponseEntity<Long> response = restTemplate.getForEntity(baseUrl + "/count", Long.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().intValue());
    }

    @Test
    public void testGetAverageAge() {
        studentRepository.save(new Student(null, "Harry Potter", 17, null));
        studentRepository.save(new Student(null, "Hermione Granger", 19, null));

        ResponseEntity<Double> response = restTemplate.getForEntity(baseUrl + "/average-age", Double.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(18.0, response.getBody(), 0.01);
    }

    @Test
    public void testGetLastFiveStudents() {
        studentRepository.save(new Student(null, "Student 1", 17, null));
        studentRepository.save(new Student(null, "Student 2", 18, null));
        studentRepository.save(new Student(null, "Student 3", 19, null));
        studentRepository.save(new Student(null, "Student 4", 20, null));
        studentRepository.save(new Student(null, "Student 5", 21, null));
        studentRepository.save(new Student(null, "Student 6", 22, null));

        ResponseEntity<Student[]> response = restTemplate.getForEntity(baseUrl + "/last-five", Student[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(5, response.getBody().length);
        assertEquals("Student 2", response.getBody()[0].getName());
    }
}
