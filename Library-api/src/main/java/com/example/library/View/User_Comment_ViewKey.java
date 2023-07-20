package com.example.library.View;

import lombok.Data;

import java.io.Serializable;

@Data
public class User_Comment_ViewKey implements Serializable {
    String nick;
    String username;
    String title;
    String review;
    String commdate;
}
