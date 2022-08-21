package com.example.MyRestProject.controller;

import com.example.MyRestProject.entity.Student;
import com.example.MyRestProject.exceptions.*;
import com.example.MyRestProject.service.StudentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController extends ExceptionController{

    @Autowired
    private StudentService studentService;

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Student> studentGetById(@PathVariable("id") Long studentId)
            throws NoSuchStudentException {
        Student httpBody = studentService.studentFindById(studentId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("userName","asrin");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(httpBody);
    }

    @GetMapping("getAll")
    public List<Student> studentGetAll(){
        return studentService.studentFindAll();
    }

    @PostMapping("/save")
    public Student studentSave(@RequestBody Student student)
            throws StudentConstructorException {

        return studentService.studentSave(student);
    }

    @PutMapping("/enroll/{studentId}/{courseId}")
    public Student studentEnroll(@PathVariable("studentId") Long studentId,
                                 @PathVariable("courseId") Long courseId)
            throws NoSuchStudentException,
            NoSuchCourseException,
            CoursePreRequisiteException,
            CourseIsNotActiveException,
            CourseMinTermRequiredException {
        return studentService.studentEnrollCourse(studentId,courseId);
    }

    @PutMapping("/drop/{studentId}/{courseId}")
    public Student studentDrop(@PathVariable("studentId") Long studentId,
                                 @PathVariable("courseId") Long courseId)
            throws NoSuchStudentException, NoSuchCourseException {
        return studentService.studentDropCourse(studentId,courseId);
    }
    @PutMapping("/updateById/{id}")
    public Student studentUpdate(@RequestBody Student student,
                                 @PathVariable("id") Long studentId)
            throws NoSuchStudentException {
        return studentService.studentUpdateById(studentId,student);
    }

}
