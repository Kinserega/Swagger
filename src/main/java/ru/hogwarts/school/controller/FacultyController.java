package ru.hogwarts.school.controller;

import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfase.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;


@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<Faculty> add(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.add(faculty));
    }

    @PutMapping
    public Faculty upDate(@RequestBody Faculty faculty) {
        return facultyService.upDate(faculty);
    }

    @GetMapping
    public Faculty get(@RequestParam Long id) {
        return facultyService.get(id);
    }

    @DeleteMapping
    public ResponseEntity<Faculty> delete(@RequestParam Long id) {
        facultyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public Collection<Faculty> getAll() {
        return facultyService.getAll();
    }

    @GetMapping("/faculty-by-color/{color}")
    public Collection<Faculty> getByColor(@PathVariable String color) {
        return facultyService.getByColor(color);
    }

    @GetMapping("/filter")
    public Collection<Faculty> getFacultyByNameOrColor(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color) {
        return facultyService.getByNameOrColorIgnorCase(name, color);
    }
    @GetMapping("/long-name-faculty")
    public String lingNameFaculty() {
        return facultyService.longNameFaculty();
    }

    @GetMapping("/students-by-id/{id}")
    public Collection<Student> getFacultyStudents(@PathVariable Long id) {
        return facultyService.getFacultyStudents(id);
    }
}
