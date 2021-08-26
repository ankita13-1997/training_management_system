package com.siemens.employee.model.dto;

public class ResponseDTO {

    private int code;
    private String message;
    private String status;
    private Object result;


    public ResponseDTO(int code, String message, String status, Object result) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.result = result;
    }

    public ResponseDTO(int code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
