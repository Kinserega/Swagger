package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.FacultyNotFoundExeption;
import ru.hogwarts.school.exeption.StudentNotFoundExeption;
import ru.hogwarts.school.interfase.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;


@Service
public class FacultyServiceImpl implements FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private FacultyRepository facultyRepository;
    private StudentRepository studentRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty add(Faculty faculty) {
        logger.info("Invoked a method for add faculty");
        Optional<Faculty> facultyExistsCheck = facultyRepository.findByName(faculty.getName());
        if (facultyExistsCheck.isPresent()) {
            logger.error("The faculty {} already exists", facultyExistsCheck);
            throw new FacultyNotFoundExeption();
        }

        Faculty addedFaculty = facultyRepository.save(faculty);
        logger.debug("Faculty {} was added", addedFaculty);
        return addedFaculty;
    }

    @Override
    public Faculty upDate(Faculty faculty) {
        logger.info("Invoked a method for upDate faculty");
        facultyIdValidation(faculty.getId());

        Faculty updatedFaculty = facultyRepository.save(faculty);
        logger.debug("Faculty {} was updated", updatedFaculty);
        return updatedFaculty;
    }

    @Override
    public void delete(Long id) {
        logger.info("Invoked a method for delete faculty");
        facultyIdValidation(id);

        logger.debug("Faculty with id {} was removed", id);
        facultyRepository.deleteById(id);
    }

    @Override
    public Faculty get(Long id) {
        logger.info("Invoked a method for get faculty");
        facultyIdValidation(id);

        Faculty getFaculty = facultyRepository.findById(id).get();
        logger.debug("Faculty with id {} was found", id);
        return getFaculty;
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.info("Invoked a method for getAll faculty");
        logger.debug("All faculties were received");
        return Collections.unmodifiableCollection(facultyRepository.findAll());
    }

    @Override
    public Collection<Faculty> getByColor(String color) {
        logger.info("Invoked a method for getByColor faculty");
        Collection<Faculty> getFacultyCollection = facultyRepository.getByColor(color);
        if (getFacultyCollection.isEmpty()) {
            logger.error("There are no faculty with color = {}", color);
            throw new FacultyNotFoundExeption();
        }

        logger.debug("Faculty with color {} were received", color);
        return getFacultyCollection;
    }

    @Override
    public Collection<Faculty> getByNameOrColorIgnorCase(String name, String color) {
        logger.info("Invoked a method for getByNameOrColorIgnorCase faculty");
        Collection<Faculty> facultyExistsCheck = facultyRepository.findByColorOrNameIgnoreCase(name, color);
        if (facultyExistsCheck.isEmpty()) {
            logger.error("There is no faculty with name = {} or color = {}", name, color);
            throw new FacultyNotFoundExeption();
        }

        logger.debug("Faculties by name = {} or color = {} were received", name, color);
        return facultyExistsCheck;
    }

    @Override
    public Collection<Student> getFacultyStudents(Long id) {
        logger.info("Invoked a method for getFacultyStudents faculty");
        facultyIdValidation(id);
        Collection<Student> studentsExistsCheck = facultyRepository.findById(id).map(Faculty::getStudents).orElse(null);
        if (studentsExistsCheck != null && studentsExistsCheck.isEmpty()) {
            logger.error("Faculty with id {} hasn't any students", id);
            throw new StudentNotFoundExeption();
        }

        logger.debug("Students of faculty with id {} were received", id);
        return studentsExistsCheck;
    }
    public String longNameFaculty() {
        logger.info("Invoked a method for longNameFaculty student");
        return facultyRepository.findAll().parallelStream()
                .filter(Objects::nonNull)
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse("Факультеты не найдены");
    }

    private void facultyIdValidation(Long id) {
        if (!facultyRepository.existsById(id)) {
            logger.error("There is no faculty with id = {}", id);
            throw new FacultyNotFoundExeption();
        }
    }
}
