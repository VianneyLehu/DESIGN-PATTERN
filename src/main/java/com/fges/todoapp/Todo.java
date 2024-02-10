package com.fges.todoapp;

public class Todo {
    private String content;
    private String author;
    private boolean done;

    public Todo(String content, String author, boolean done) {
        this.content = content;
        this.author = author;
        this.done = done;
    }

    // Getters and setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
