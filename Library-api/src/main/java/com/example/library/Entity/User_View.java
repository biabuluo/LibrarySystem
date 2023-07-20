package com.example.library.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User_View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    int uid;

    String username;
    String password;

    String name;

    String nick;
    String img;
    String motto;

    String phone;

    boolean isadmin;

    int borrownums;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    public boolean getIsadmin () {
        return this.isadmin;
    }

    public int getBorrownums() {
        return borrownums;
    }

    public void setBorrownums(int borrownums) {
        this.borrownums = borrownums;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}


