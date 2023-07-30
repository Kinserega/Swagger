package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exeption.StudentNotFoundExeption;
import ru.hogwarts.school.interfase.StudentService;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private  final Map<Long, Student> studentMap = new HashMap<>();
    private static long ID_COUNTER = 1;

    @Override
    public Student add(Student student) {
        Student newStuden = new Student(ID_COUNTER, student.getName(), student.getAge());
        ID_COUNTER++;
        studentMap.put(newStuden.getId(), newStuden);
        return newStuden;
    }

    @Override
    public Student upDate(Student student) {
        Student studentForUpdate = studentMap.get(student.getId());
        if (studentForUpdate == null) {
            throw new StudentNotFoundExeption();
        }
        studentForUpdate.setName(student.getName());
        studentForUpdate.setAge(student.getAge());
        return studentForUpdate;
    }

    @Override
    public Student delete(Long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundExeption();
        }
        return studentMap.remove(id);
    }

    @Override
    public Student get(Long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundExeption();
        }
        return studentMap.get(id);
    }

    @Override
    public Map<Long, Student> getAll() {
        return studentMap;
    }

    @Override
    public List<Student> getByAge(int age) {
        return studentMap.values().stream().
                filter(student -> student.getAge() == age).
                collect(Collectors.toList());
    }
}
