package com.example.MyRestProject.entity;

import java.sql.Timestamp;

public class ErrorResponse {

    private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    private Integer status;

    private String error;

    private String errorMessage;

    private String value;

    public ErrorResponse(Integer status, String error,String errorMessage,String value) {
        this.status = status;
        this.error = error;
        this.errorMessage = errorMessage;
        this.value = value;
    }
}
