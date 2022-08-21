package com.example.MyRestProject.exceptions;

public class NoSuchStudentException extends Exception{

    public NoSuchStudentException(String errorMessage){
        super(errorMessage);
    }
}
