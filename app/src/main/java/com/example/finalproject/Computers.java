package com.example.finalproject;

public class Computers {
    private int status;
    private int time;

    public Computers() {}

    public Computers(int status, int time, int id) {
        this.status = status;
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
