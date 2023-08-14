package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.interfase.GetStudentCategory;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.Collection;

@RestController
public class GetStudentCategoryController {
    private final StudentServiceImpl studentService;

    public GetStudentCategoryController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get-all-student")
    public Integer getAllStudent() {
        return studentService.amountAllStudents();
    }
    @GetMapping("/medium-age-student")
    public Integer mediumAgeStudent() {
        return studentService.mediumAgeStudent();
    }
    @GetMapping("/last-fife-student")
    public Collection<Student> lastFifeStudent() {
        return studentService.lastFifeStudent();
    }

}
