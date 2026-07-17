package org.mateuszlaski.logon.common.response;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionResponse {
    private LocalDateTime timestamp;
    private int status;
    private List<String> errors;
    private String path;

    public ExceptionResponse() { }

    public ExceptionResponse(LocalDateTime timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        errors = List.of(error);
        this.path = path;
    }

    public ExceptionResponse(LocalDateTime timestamp, int status, List<String> errors, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
