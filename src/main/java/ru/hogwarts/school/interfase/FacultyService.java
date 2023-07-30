package ru.hogwarts.school.interfase;

import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.Map;

public interface FacultyService {
    Faculty add(Faculty faculty);

    Faculty upDate(Faculty faculty);

    Faculty delete(Long id);

    Faculty get(Long id);

    Map<Long, Faculty> getAll();

    List<Faculty> getByColor(String color);
}
