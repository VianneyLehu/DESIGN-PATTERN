package com.fges.todoapp;

public class Todo {
    private String content;
    private boolean done;

    public Todo(String content, boolean done) {
        this.content = content;
        this.done = done;
    }

    // Getters and setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
