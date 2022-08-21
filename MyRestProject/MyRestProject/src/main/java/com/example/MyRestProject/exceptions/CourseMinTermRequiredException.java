package com.example.MyRestProject.exceptions;

public class CourseMinTermRequiredException extends Exception{

    public CourseMinTermRequiredException(String errorMessage){
        super(errorMessage);
    }
}
