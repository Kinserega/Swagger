package ru.hogwarts.school.interfase;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student add(Student student);

    Student upDate(Student student);

    void delete(Long id);

    Student get(Long id);

    Collection<Student> getAll();

    Collection<Student> getByAge(int id);
}
