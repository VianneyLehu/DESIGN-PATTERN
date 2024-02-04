package com.fges.todoapp;

import java.io.IOException;

public interface CommandHandler {
    void handle(String[] args, String fileName, boolean isDone) throws IOException;
}