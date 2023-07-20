package com.example.library.Result;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result(int code) {
        this.code = code;
    }

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
