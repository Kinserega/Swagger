package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfase.StudentService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.stream.Stream;

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
    public ResponseEntity<Student> delete(@RequestParam Long id) {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public Collection<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/student-start-lette")
    public Collection<String> findStudentsWithStartLetteA() {
        return studentService.findStudentsWithStartLetteA();
    }

    @GetMapping("/middle-age-student")
    public Integer middleAgeStudent() {
        return studentService.middleAgeStudent();
    }

    @GetMapping("/studet-by-age/{age}")
    public Collection<Student> getByAge(@PathVariable int age) {
        return studentService.getByAge(age);
    }

    @GetMapping("/filterStudent")
    public Collection<Student> getStudentBetween(@RequestParam int min,
                                                 @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("/thread-student")
    public void threadStudent() {
        studentService.threadStudent();
    }

    @GetMapping("/thread-student-synchron")
    public void threadStudentSynchronized() {
        studentService.threadStudentSynchronized();
    }


    @GetMapping("fakulty-student/{id}")
    public Faculty getFakultyByStudent(@PathVariable Long id) {
        return studentService.findStudentByIdFaculty(id);
    }

    @GetMapping("/parallelStream")
    public int parallelStream() {
        int sum = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        return sum;
    }
}
