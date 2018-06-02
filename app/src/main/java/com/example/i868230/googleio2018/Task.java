package com.example.i868230.googleio2018;

public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        String check = done ? "[x]" : "[  ]";
        return String.format("%s %s", check, description);
    }
}
