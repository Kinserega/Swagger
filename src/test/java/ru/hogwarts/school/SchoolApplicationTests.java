package ru.hogwarts.school;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {

    @LocalServerPort
    private int port;
    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contexLoads() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .isNotNull();
    }

    @Test
    void getStudentTest() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotEmpty();
    }

    @Test
    void postStudentTest() throws Exception {
        Student student = new Student(22L, "Petr", 25);
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotEmpty();
    }

    @Test
    void testFindStudentById() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/student/13", Student.class))
                .isNotNull();
    }

    @Test
    void testGetAllStudents() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/student/all", Collection.class))
                .isNotNull();
    }

    @Test
    void testFindStudentByAge() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/student/student-by-age?age=15", Student.class))
                .isNotNull();
    }

    @Test
    void testFindStudentByAgeBetween() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/student/filterStudent?min=12&max=20", Collection.class))
                .isNotNull();
    }

    @Test
    void testGetStudentFaculty() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/student/faculty/12", Faculty.class))
                .isNotNull();
    }

    @Test
    void testEditStudent() throws Exception {
        Student student = new Student(13L, "Петр", 20);
        ResponseEntity<Student> exchange = restTemplate.exchange("http://localhost:" + port + "/student",
                HttpMethod.PUT,
                new HttpEntity<>(student),
                Student.class);
        Assertions
                .assertThat(exchange.getBody())
                .isNotNull();
    }
    @Test
    void testDeleteStudent() throws Exception {
        Student student = new Student(13L, "Петр", 20);
        ResponseEntity<Student> exchange = restTemplate.exchange("http://localhost:" + port + "/student",
                HttpMethod.DELETE,
                new HttpEntity<>(student),
                Student.class);
        Assertions
                .assertThat(exchange.getBody())
                .isNotNull();
    }


    @Test
    void testAddFaculty() throws Exception {
        Faculty faculty = new Faculty(5L, "Gryff", "Red");

        Assertions
                .assertThat(this.restTemplate.postForObject(
                        "http://localhost:" + port + "/faculty", faculty, Faculty.class))
                .isNotNull();
    }

    @Test
    void testFindFacultyById() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/1", Faculty.class))
                .isNotNull();
    }

    @Test
    void testGetAllFaculties() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/all", Collection.class))
                .isNotNull();
    }

    @Test
    void testGetFacultiesByNameOrColourIgnoreCase() throws Exception {
        Assertions
                .assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/faculty/faculty-by-color?name=green", Collection.class))
                .isNotNull();
    }

    @Test
    void testEditFaculty() throws Exception {
        Faculty faculty = new Faculty(13L, "First", "green");
        ResponseEntity<Faculty> exchange = restTemplate.exchange("http://localhost:" + port + "/faculty",
                HttpMethod.PUT,
                new HttpEntity<>(faculty),
                Faculty.class);
        Assertions
                .assertThat(exchange.getBody())
                .isNotNull();
    }
    @Test
    void testDeleteFaculty() throws Exception {
        Faculty faculty = new Faculty(13L, "First", "green");
        ResponseEntity<Faculty> exchange = restTemplate.exchange("http://localhost:" + port + "/faculty",
                HttpMethod.DELETE,
                new HttpEntity<>(faculty),
                Faculty.class);
        Assertions
                .assertThat(exchange.getBody())
                .isNotNull();
    }
}

