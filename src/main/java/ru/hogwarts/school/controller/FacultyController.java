package ru.hogwarts.school.controller;

import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.interfase.FacultyService;
import ru.hogwarts.school.model.Faculty;


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
    public ResponseEntity delete(@RequestParam Long id) {
        facultyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public Collection<Faculty> getAll() {
        return facultyService.getAll();
    }
    @GetMapping("/faculty-by-color")
    public Collection<Faculty> getByColor(String color) {
        return facultyService.getByColor(color);
    }
}
