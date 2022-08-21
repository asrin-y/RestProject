package com.example.MyRestProject.controller;

import com.example.MyRestProject.exceptions.*;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExceptionController {

    @ExceptionHandler(value = NoSuchCourseException.class)
    public String handleNoSuchCourseException(Exception e){
        String courseId = e.getMessage();
        return "No such course exists with courseId: " + courseId;
    }

    @ExceptionHandler(value = NoSuchStudentException.class)
    public String handleNoSuchStudentException(Exception e){
        String studentId = e.getMessage();
        return "No such student exists with studentId: " + studentId;
    }

    @ExceptionHandler(value = StudentConstructorException.class)
    public String handleStudentConstructorException(Exception e){
        return "Student object cannot be initialized without following fields:\n" +
                "studentTerm\n" +
                "studentTotalCredit\n" +
                "studentGpa\n" +
                "studentIsActive\n";
    }

    @ExceptionHandler(value = CourseConstructorException.class)
    public String handleCourseConstructorException(Exception e){
        return "Course object cannot be initialized without following fields:\n" +
                "courseMinTermRequired\n" +
                "courseCredit\n" +
                "courseIsActive\n";
    }

    @ExceptionHandler(value = CoursePreRequisiteException.class)
    public String handleCoursePreRequisiteException(CoursePreRequisiteException e){
        List<Long> missingPrerequisites = e.getMissingPrerequisites();
        return "studentId: " +
                e.getStudentId().toString()+
                " has not taken following courses which are prerequisites for courseId: "+
                e.getCourseId().toString()+
                "\n"+
                e.getMissingPrerequisites().toString();
    }
}