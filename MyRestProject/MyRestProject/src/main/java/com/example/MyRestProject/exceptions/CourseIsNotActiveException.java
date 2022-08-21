package com.example.MyRestProject.exceptions;

public class CourseIsNotActiveException extends Exception{
    public CourseIsNotActiveException(String errorMessage){
        super(errorMessage);
    }
}
