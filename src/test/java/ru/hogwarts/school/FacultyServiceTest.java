package ru.hogwarts.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.interfase.FacultyService;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacultyServiceTest {
    @Mock
    private FacultyRepository facultyRepository;
    private FacultyService facul;
    @BeforeEach
    public void setUp() {
        facul = new FacultyServiceImpl(facultyRepository);
    }

    @Test
    void addFaculty() {
        Faculty faculty = new Faculty(0L, "Gryffindor", "Red and gold");

        when(facultyRepository.save(eq(faculty))).thenReturn(faculty);
        assertEquals(faculty, facul.add(faculty));
        verify(facultyRepository, only()).save(faculty);
    }

    @Test
    void findFaculty() {
        Optional<Faculty> optionalFaculty = Optional.of(new Faculty(1L, "Gryffindor", "Red and gold"));
        Faculty faculty = new Faculty(1L, "Gryffindor", "Red and gold");

        when(facultyRepository.findById(1L)).thenReturn(optionalFaculty);
        assertEquals(faculty, facul.get(1L));
        verify(facultyRepository, only()).findById(1L);
    }

    @Test
    void editFaculty() {
        Faculty faculty = new Faculty(3L, "Gryffindor", "Red and gold");

        when(facultyRepository.save(eq(faculty))).thenReturn(faculty);
        assertEquals(faculty, facul.upDate(faculty));
        verify(facultyRepository, only()).save(faculty);
    }

    @Test
    void getAllFaculties() {
        List<Faculty> facultyList = new ArrayList<>();
        Faculty faculty1 = new Faculty(1L, "Gryffindor", "Red and gold");
        Faculty faculty2 = new Faculty(2L, "Hufflepuff", "Yellow and black");
        Faculty faculty3 = new Faculty(3L, "Slytherin", "Green and silver");
        facultyList.add(faculty1);
        facultyList.add(faculty2);
        facultyList.add(faculty3);

        when(facultyRepository.findAll()).thenReturn(facultyList);
        Collection<Faculty> faculties = facul.getAll();
        assertEquals(3, faculties.size());
    }

    @Test
    void getFacultiesByColour() {
        List<Faculty> facultyList = new ArrayList<>();
        Faculty faculty1 = new Faculty(1L, "Gryffindor", "Red and gold");
        Faculty faculty2 = new Faculty(2L, "Gryffindor", "Red and gold");
        facultyList.add(faculty1);
        facultyList.add(faculty2);

        when(facultyRepository.getByColor("Red and gold")).thenReturn(facultyList);
        Collection<Faculty> faculties = facul.getByColor("Red and gold");
        assertEquals(2, faculties.size());
    }
}
