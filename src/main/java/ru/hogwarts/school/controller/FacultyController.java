package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfase.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
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
    public Faculty delete(@RequestParam Long id) {
        return facultyService.delete(id);
    }

    @GetMapping("/all")
    public Map<Long, Faculty> getAll() {
        return facultyService.getAll();
    }
    @GetMapping("/faculty-by-color")
    public List<Faculty> getByColor(String color) {
        return facultyService.getByColor(color);
    }
}
