package com.example.MyRestProject.exceptions;

public class NoSuchCourseException extends Exception{

    public NoSuchCourseException(String errorMessage){
        super(errorMessage);
    }
}
