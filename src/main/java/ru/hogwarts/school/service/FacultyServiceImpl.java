package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.FacultyNotFoundExeption;
import ru.hogwarts.school.interfase.FacultyService;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> facultyMap = new HashMap<>();
    private static long ID_COUNTER = 1;

    @Override
    public Faculty add(Faculty faculty) {
        Faculty newFaculty = new Faculty(ID_COUNTER, faculty.getName(), faculty.getColor());
        ID_COUNTER++;
        facultyMap.put(newFaculty.getId(), newFaculty);
        return newFaculty;
    }

    @Override
    public Faculty upDate(Faculty faculty) {
        Faculty facultyForUpDate = facultyMap.get(faculty.getId());
        if (facultyForUpDate == null) {
            throw new FacultyNotFoundExeption();
        }
        facultyForUpDate.setName(faculty.getName());
        facultyForUpDate.setColor(faculty.getColor());
        return facultyForUpDate;
    }

    @Override
    public Faculty delete(Long id) {
        if (facultyMap.containsKey(id)) {
            throw new FacultyNotFoundExeption();
        }
        return facultyMap.remove(id);
    }

    @Override
    public Faculty get(Long id) {
        if (facultyMap.containsKey(id)) {
            throw new FacultyNotFoundExeption();
        }
        return facultyMap.get(id);
    }

    @Override
    public Map<Long, Faculty> getAll() {
        return facultyMap;
    }

    @Override
    public List<Faculty> getByColor(String color) {
        return facultyMap.values().stream().
                filter(faculty -> faculty.getColor().equals(color)).
                collect(Collectors.toList());
    }
}
