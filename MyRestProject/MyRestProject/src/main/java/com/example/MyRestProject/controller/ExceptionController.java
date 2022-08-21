package com.example.MyRestProject.controller;

import com.example.MyRestProject.entity.ErrorResponse;
import com.example.MyRestProject.exceptions.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class ExceptionController {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @ExceptionHandler(value = NoSuchCourseException.class)
    public ResponseEntity<String> handleNoSuchCourseException(Exception e){
        String courseId = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Bad Request","No course exists with courseId: " + courseId,
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(errorResponse));
    }

    @ExceptionHandler(value = NoSuchStudentException.class)
    public ResponseEntity<String> handleNoSuchStudentException(Exception e){
        String studentId = e.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                "No student exists with studentId: " + studentId,
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(errorResponse));
    }

    @ExceptionHandler(value = StudentConstructorException.class)
    public ResponseEntity<String> handleStudentConstructorException(Exception e){
        List<String> requiredFields = new java.util.ArrayList<>(Collections.emptyList());
        requiredFields.add("studentTerm");
        requiredFields.add("studentTotalCredit");
        requiredFields.add("studentGpa");
        requiredFields.add("studentIsActive");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                "Student object cannot be created without following fields: " +
                        String.join(", ",requiredFields),
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(errorResponse));
    }

    @ExceptionHandler(value = CourseConstructorException.class)
    public ResponseEntity<String> handleCourseConstructorException(Exception e){
        List<String> requiredFields = new java.util.ArrayList<>(Collections.emptyList());
        requiredFields.add("courseMinTermRequired");
        requiredFields.add("courseCredit");
        requiredFields.add("courseIsActive");
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                "Course object cannot be created without following fields: " +
                        String.join(", ",requiredFields),
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(errorResponse));
    }

    @ExceptionHandler(value = CoursePreRequisiteException.class)
    public ResponseEntity<String> handleCoursePreRequisiteException(CoursePreRequisiteException e){
        List<Long> missingPrerequisites = e.getMissingPrerequisites();
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                "studentId: " +
                        e.getStudentId().toString()+
                        " has not taken following courses which are prerequisites for courseId: "+
                        e.getCourseId().toString() +
                        " " +
                        missingPrerequisites.toString(),
                missingPrerequisites.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(gson.toJson(errorResponse));
    }

    @ExceptionHandler(value = CourseIsNotActiveException.class)
    public String handleCourseIsNotActiveException(Exception e){
        String errorMessage = e.getMessage();
        return "courseId: " + errorMessage + " is not active.";
    }

    @ExceptionHandler(value = CourseMinTermRequiredException.class)
    public String handleCourseMinTermRequiredException(CourseMinTermRequiredException e){
        Long studentId = e.getStudentId();
        Long courseId = e.getCourseId();
        return "studentId: " + studentId.toString() +
                " does not fulfill the minimum term requirement for courseId: " +
                courseId.toString();
    }
}