package ru.hogwarts.school.interfase;

import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface GetStudentCategory {

    Integer amountAllStudents();

    Integer mediumAgeStudent();

    Collection<Student> lastFifeStudent();

}
