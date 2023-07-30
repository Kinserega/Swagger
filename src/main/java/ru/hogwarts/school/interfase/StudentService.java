package ru.hogwarts.school.interfase;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    Student add(Student student);

    Student upDate(Student student);

    Student delete(Long id);

    Student get(Long id);

    Map<Long, Student> getAll();

    List<Student> getByAge(int id);
}
