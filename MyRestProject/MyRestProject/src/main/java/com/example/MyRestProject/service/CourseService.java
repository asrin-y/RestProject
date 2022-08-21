package com.example.MyRestProject.service;

import com.example.MyRestProject.entity.Course;
import com.example.MyRestProject.exceptions.CourseConstructorException;
import com.example.MyRestProject.exceptions.NoSuchCourseException;

import java.util.List;

public interface CourseService {

    //save
    Course courseSave(Course course) throws CourseConstructorException;

    //enroll
    void courseEnroll(Long studentId, Long courseId) throws NoSuchCourseException;

    //drop
    void courseDrop(Long studentId, Long courseId) throws NoSuchCourseException;

    //get by course id
    Course courseGetById(Long courseId) throws NoSuchCourseException;

    //get by course name
    List<Course> courseGetByName(String courseName);

    //get all
    List<Course> courseGetAll();

    //delete
    String courseDelete(Long courseId) throws NoSuchCourseException;

    //add prerequisite
    Course courseAddPreRequisite(Long courseId, Long preRequisiteId) throws NoSuchCourseException;

}
