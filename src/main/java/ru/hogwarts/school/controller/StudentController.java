package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfase.StudentService;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping
    public Student upDate(@RequestBody Student student) {
        return studentService.upDate(student);
    }

    @GetMapping
    public Student get(@RequestParam Long id) {
        return studentService.get(id);
    }

    @DeleteMapping
    public Student delete(@RequestParam Long id) {
        return studentService.delete(id);
    }

    @GetMapping("/all")
    public Map<Long, Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/studet-by-age")
    public List<Student> getByAge(int age) {
        return studentService.getByAge(age);
    }
}
