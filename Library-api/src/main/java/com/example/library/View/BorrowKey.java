package com.example.library.View;

import lombok.Data;

import java.io.Serializable;

@Data
public class BorrowKey implements Serializable {
    private int uid;
    private int bid;
}
