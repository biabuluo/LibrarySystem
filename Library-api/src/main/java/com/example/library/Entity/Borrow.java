package com.example.library.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="borrow")
@IdClass(BorrowKey.class)
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Borrow {
    @Id
    @Column(name = "uid")
    int uid;

    @Id
    @Column(name = "bid")
    int bid;


    String borrowdate;

    public String getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(String borrowdate) {
        this.borrowdate = borrowdate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getBid() {
        return bid;
    }
}

