package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfase.StudentService;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
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
    public ResponseEntity delete(@RequestParam Long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public Collection<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/studet-by-age")
    public Collection<Student> getByAge(int age) {
        return studentService.getByAge(age);
    }
}
