package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.FacultyNotFoundExeption;
import ru.hogwarts.school.exeption.StudentAlreadyExistsException;
import ru.hogwarts.school.exeption.StudentNotFoundExeption;
import ru.hogwarts.school.interfase.GetStudentCategory;
import ru.hogwarts.school.interfase.StudentService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService, GetStudentCategory {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    private StudentRepository studentRepository;
    private AvatarRepository avatarRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    public StudentServiceImpl(StudentRepository studentRepository) {
    }

    @Override
    public Student add(Student student) {
        logger.info("Invoked a method for add student");
        Optional<Student> studentExistsCheck = studentRepository.findByName(student.getName());
        if (studentExistsCheck.isPresent()) {
            logger.error("The student {} already exists", studentExistsCheck);
            throw new StudentAlreadyExistsException();
        }

        Student addStudent = studentRepository.save(student);
        logger.debug("Student {} was added", addStudent);
        return addStudent;
    }

    @Override
    public Student upDate(Student student) {
        logger.info("Invoked a method for upDate student");
        studentIdValidation(student.getId());

        Student updatedStudent = studentRepository.save(student);
        logger.debug("Student {} was updated", updatedStudent);
        return updatedStudent;
    }

    @Override
    public void delete(Long id) {
        logger.info("Invoked a method for delete student");
        studentIdValidation(id);

        logger.debug("Student with id {} was removed", id);
        avatarRepository.deleteByStudentId(id);
        studentRepository.deleteById(id);
    }

    @Override
    public Student get(Long id) {
        logger.info("Invoked a method for get student");
        studentIdValidation(id);

        Student getStudent = studentRepository.findById(id).get();
        logger.debug("Student with id {} was found", id);
        return getStudent;
    }

    @Override
    public Collection<Student> getAll() {
        logger.info("Invoked a method for getAll student");
        logger.debug("All students were received");
        return Collections.unmodifiableCollection(studentRepository.findAll());
    }

    @Override
    public Collection<Student> getByAge(int age) {
        logger.info("Invoked a method for getByAge student");
        Collection<Student> foundStudentsList = studentRepository.findByAge(age);
        if (foundStudentsList.isEmpty()) {
            logger.error("There are no students with age = {}", age);
            throw new StudentNotFoundExeption();
        }

        logger.debug("Students with age {} were received", age);
        return foundStudentsList;
    }
    @Override
    public Collection<String> findStudentsWithStartLetteA() {
        logger.info("Invoked a method for findStudentsWithStartLetteA student");
        return studentRepository.findAll().parallelStream()
                        .filter(Objects::nonNull)
                                .map(Student::getName)
                                                .filter(name->name.startsWith("А"))
                                                      .sorted()
                                                        .collect(Collectors.toList());
    }
    public Integer middleAgeStudent() {
        logger.info("Invoked a method for middleAgeStudent student");
        return (int) studentRepository.findAll().parallelStream()
                .filter(Objects::nonNull)
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
    }

    @Override
    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Invoked a method for findByAgeBetween student");
        Collection<Student> foundStudentsList = studentRepository.findByAgeBetween(min, max);
        if (foundStudentsList.isEmpty()) {
            logger.error("There are no students with age between {} and {}", min, max);
            throw new StudentNotFoundExeption();
        }

        logger.debug("Students with age between {} and {} were received", min, max);
        return foundStudentsList;
    }

    @Override
    public Faculty findStudentByIdFaculty(Long id) {
        logger.info("Invoked a method for findStudentByIdFaculty student");
        studentIdValidation(id);
        Faculty faculty = studentRepository.findById(id).map(Student::getFaculty).orElse(null);
        if (faculty == null) {
            logger.warn("Student with id {} hasn't faculty", id);
            throw new FacultyNotFoundExeption();
        }

        logger.debug("Faculty of student with id {} was received", id);
        return faculty;
    }

    @Override
    public Integer amountAllStudents() {
        logger.info("Invoked a method for amountAllStudents student");
        return studentRepository.amountAllStudents();
    }

    @Override
    public Integer mediumAgeStudent() {
        logger.info("Invoked a method for mediumAgeStudent student");
        return studentRepository.mediumAgeStudent();
    }

    @Override
    public Collection<Student> lastFifeStudent() {
        logger.info("Invoked a method for lastFifeStudent student");
        return studentRepository.lastFideStudent();
    }

    private void studentIdValidation(Long id) {
        if (!studentRepository.existsById(id)) {
            logger.error("There is no student with id = {}", id);
            throw new StudentNotFoundExeption();
        }
    }

}
