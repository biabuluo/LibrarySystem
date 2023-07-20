package com.example.library.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BorrowKey implements Serializable {
    int uid;
    int bid;
}
