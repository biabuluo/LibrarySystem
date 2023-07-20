package com.example.library.View;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "user_comment_info")
@IdClass(User_Comment_ViewKey.class)
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User_Comment_View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nick")
    String nick;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "title")
    String title;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username")
    String username;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review")
    String review;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commdate")
    String commdate;

    public String getCommdate() {
        return commdate;
    }

    public void setCommdate(String commdate) {
        this.commdate = commdate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
