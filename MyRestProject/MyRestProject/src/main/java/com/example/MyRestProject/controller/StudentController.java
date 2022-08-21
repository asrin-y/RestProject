package com.example.MyRestProject.controller;

import com.example.MyRestProject.entity.Student;
import com.example.MyRestProject.exceptions.*;
import com.example.MyRestProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController extends ExceptionController{

    @Autowired
    private StudentService studentService;

    @GetMapping("/getById/{id}")
    public Student studentGetById(@PathVariable("id") Long studentId)
            throws NoSuchStudentException {
        return studentService.studentFindById(studentId);
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
