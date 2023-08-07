package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.interfase.StudentService;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    private StudentService st;

    @BeforeEach
    public void setUp() {
        st = new StudentServiceImpl(studentRepository);
    }
    @Test
    void addStudent() {
        Student student = new Student(0L, "Harry", 14);

        when(studentRepository.save(eq(student))).thenReturn(student);
        assertEquals(student, st.add(student));
        verify(studentRepository, only()).save(student);
    }

    @Test
    void findStudent() {
        Optional<Student> optionalStudent = Optional.of(new Student(1L, "Harry", 14));
        Student student = new Student(1L, "Harry", 14);

        when(studentRepository.findById(1L)).thenReturn(optionalStudent);
        assertEquals(student, st.get(1L));
        verify(studentRepository, only()).findById(1L);
    }

    @Test
    void editStudent() {
        Student student = new Student(3L, "Harry", 14);

        when(studentRepository.save(eq(student))).thenReturn(student);
        assertEquals(student, st.upDate(student));
        verify(studentRepository, only()).save(student);
    }

    @Test
    void getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "Harry", 14);
        Student student2 = new Student(2L, "Ron", 14);
        Student student3 = new Student(3L, "Hermione", 15);
        studentList.add(student);
        studentList.add(student2);
        studentList.add(student3);

        when(studentRepository.findAll()).thenReturn(studentList);
        Collection<Student> students = st.getAll();
        assertEquals(3, students.size());
    }

    @Test
    void getStudentsByAge() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "Harry", 23);
        Student student2 = new Student(2L, "Ron", 23);
        studentList.add(student);
        studentList.add(student2);

        when(studentRepository.findByAge(23)).thenReturn(studentList);
        Collection<Student> students = st.getByAge(23);
        assertEquals(2, students.size());
    }
    @Test
    void getStudentsByAgeBetween() {
        List<Student> studentList = new ArrayList<>();
        Student student = new Student(1L, "Иван", 32);
        Student student2 = new Student(2L, "Петр", 12);
        Student student3 = new Student(3L, "Сергей", 32);
        Student student4 = new Student(4L, "Николай", 19);
        studentList.add(student);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        when(studentRepository.findByAgeBetween(13, 16)).thenReturn(studentList);
        Collection<Student> students = st.findByAgeBetween(13, 16);
        assertEquals(4, students.size());
        verify(studentRepository, only()).findByAgeBetween(13, 16);
    }
}
