package com.example.library.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentKey implements Serializable {
    int uid;
    String isbn;
    float level;
    String commdate;
    String review;

}
