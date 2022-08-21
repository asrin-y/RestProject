package com.example.MyRestProject.controller;

import com.example.MyRestProject.entity.Course;
import com.example.MyRestProject.exceptions.CourseConstructorException;
import com.example.MyRestProject.exceptions.NoSuchCourseException;
import com.example.MyRestProject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course")
public class CourseController extends ExceptionController{

    @Autowired
    private CourseService courseService;

    @PostMapping("/save")
    public Course courseSave(@RequestBody Course course)
            throws CourseConstructorException {
        return courseService.courseSave(course);
    }

    @GetMapping("/getAll")
    public List<Course> courseGetAll(){
        return courseService.courseGetAll();
    }

    @GetMapping("/getById/{id}")
    public Course courseGetById(@PathVariable("id") Long courseId) throws NoSuchCourseException {

        return courseService.courseGetById(courseId);
    }

    @GetMapping("/getByName/{name}")
    public List<Course> courseGetByName(@PathVariable("name") String courseName){
        return courseService.courseGetByName(courseName);
    }

    /*
    @DeleteMapping("/delete/{id}")
    public String courseDelete(@PathVariable("id") Long courseId)
            throws NoSuchCourseException {
        return courseService.courseDelete(courseId);
    }*/
}
