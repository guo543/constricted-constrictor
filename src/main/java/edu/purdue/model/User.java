package edu.purdue.model;

import java.util.ArrayList;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private HighScores highScores;

    public User() {
        highScores = new HighScores();
    }

    public User(int id, String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        highScores = new HighScores();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HighScores getHighScores() {
        return highScores;
    }

    public void setHighScores(HighScores highScores) {
        this.highScores = highScores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", highScores=" + highScores +
                '}';
    }
}
