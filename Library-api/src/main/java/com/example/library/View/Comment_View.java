package com.example.library.View;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "comment_info")
@IdClass(Comment_ViewKey.class)
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Comment_View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nick")
    String nick;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img")
    String img;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title")
    String title;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review")
    String review;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commdate")
    String commdate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level")
    float level;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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
