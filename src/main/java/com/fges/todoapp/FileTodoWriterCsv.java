package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTodoWriterCsv {
    private String fileName;

    public FileTodoWriterCsv(String fileName) {
        this.fileName = fileName;
    }

    public void insertTodo(Todo todo) throws IOException {
        String line = "\"" + todo.getContent() + "\"," + todo.isDone() + "\n";
        Files.write(Paths.get(fileName), line.getBytes(), java.nio.file.StandardOpenOption.CREATE, java.nio.file.StandardOpenOption.APPEND);
    }
}
