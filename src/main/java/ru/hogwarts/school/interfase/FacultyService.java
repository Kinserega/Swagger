package ru.hogwarts.school.interfase;

import java.util.Collection;
import ru.hogwarts.school.model.Faculty;

import java.util.List;
import java.util.Map;

public interface FacultyService {
    Faculty add(Faculty faculty);

    Faculty upDate(Faculty faculty);

    void delete(Long id);

    Faculty get(Long id);

    Collection<Faculty> getAll();

    Collection<Faculty> getByColor(String color);
}
