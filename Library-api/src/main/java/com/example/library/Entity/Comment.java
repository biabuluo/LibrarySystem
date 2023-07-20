package com.example.library.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="comment")
@IdClass(CommentKey.class)
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Comment {
    @Id
    @Column(name = "uid")
    int uid;

    @Id
    @Column(name = "level")
    float level;

    @Id
    @Column(name = "review")
    String review;

    @Id
    @Column(name = "commdate")
    String commdate;

    @Id
    @Column(name = "ISBN")
    String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public String getCommdate() {
        return commdate;
    }

    public void setCommdate(String commdate) {
        this.commdate = commdate;
    }
}
