package com.cjc.filesystem.domain;

import java.io.Serializable;
public class User implements Serializable {
    private int id;
    private String name;
    private String password;
    private int score;

    public User() {
    }

    public User(int id, String name, String password, int score) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
