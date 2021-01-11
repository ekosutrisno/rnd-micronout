package com.ekosutrisno;

import io.micronaut.http.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

/**
 * @author Eko Sutrisno
 * Monday, 11/01/2021 12:55
 */
@Controller(value = "/students", port = "9091")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class AppController {

    Set<Student> students = new LinkedHashSet<>();

    public AppController() {
        students.addAll(
                Arrays.asList(
                        new Student("684c4fc4-d992-4f95-9479-336d6e065c59", 12, "Eko Sutrisno"),
                        new Student("bacf044c-e87b-4e4a-a422-17fe958766e4", 12, "Albert")
                )
        );
    }

    @Get
    public Set<Student> getStudents() {
        return students;
    }

    @Get("/{studentId}")
    public Student getStudent(@PathVariable("studentId") String studentId) {
        return students.stream()
                .filter(student -> student.studentId.equals(studentId))
                .findFirst().orElse(null);
    }

    @Post
    public Set<Student> registerStudent(@Valid @Body Student student) {
        students.add(student);
        return students;
    }

    @Delete("/{studentId}")
    public Set<Student> deleteStudent(@Valid @Body Student student) {
        students.remove(student);
        return students;
    }

    static class Student {
        private String studentId;
        private int grade;
        private String username;

        public Student(String studentId, int grade, String username) {
            this.studentId = studentId;
            this.grade = grade;
            this.username = username;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getStudentId() {
            return studentId;
        }

        public Student() {
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}

