package com.fges.todoapp;

import java.io.IOException;

public interface CommandHandler {
    void handle(String[] args, TodoRepository repository, boolean isDone, String author) throws IOException;
}