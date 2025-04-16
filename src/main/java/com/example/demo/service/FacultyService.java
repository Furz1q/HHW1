package com.example.demo.service;

import com.example.demo.model.Faculty;
import com.example.demo.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FacultyService {

    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> searchFaculties(String name, String color) {
        logger.info("Was invoked method for searching faculties by name or color");
        logger.debug("Searching faculties by name = '{}' or color = '{}'", name, color);
        List<Faculty> faculties = facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
        logger.debug("Found faculties: {}", faculties);
        if (faculties.isEmpty()) {
            logger.warn("No faculties found with name = '{}' or color = '{}'", name, color);
        }
        return faculties;
    }

    public Optional<Faculty> getFacultyById(Long id) {
        logger.info("Was invoked method for getting faculty by id");
        logger.debug("Trying to find faculty with id = {}", id);
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isEmpty()) {
            logger.error("There is no faculty with id = {}", id);
        } else {
            logger.debug("Found faculty: {}", faculty.get());
        }
        return faculty;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for creating faculty");
        logger.debug("Creating faculty with data: {}", faculty);
        Faculty savedFaculty = facultyRepository.save(faculty);
        logger.debug("Faculty saved: {}", savedFaculty);
        return savedFaculty;
    }

    public boolean deleteFaculty(Long id) {
        logger.info("Was invoked method for deleting faculty");
        logger.debug("Trying to delete faculty with id = {}", id);
        if (facultyRepository.existsById(id)) {
            facultyRepository.deleteById(id);
            logger.debug("Faculty with id = {} successfully deleted", id);
            return true;
        } else {
            logger.warn("Faculty with id = {} does not exist, delete skipped", id);
            return false;
        }
    }
}
