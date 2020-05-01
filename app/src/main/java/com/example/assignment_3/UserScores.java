package com.example.assignment_3;

public class UserScores {

    private int id;
    private String name;
    private int score;

    UserScores(){

    }

    UserScores(String name, int score){
        this.name = name;
        this.score = score;
    }

    UserScores(int id, String name, int score){
        this.id = id;
        this.name = name;
        this.score = score;
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

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }
}
