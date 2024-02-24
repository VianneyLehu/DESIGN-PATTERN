package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileTodoReaderCsv {
    private String fileName;

    public FileTodoReaderCsv(String fileName) {
        this.fileName = fileName;
    }

    public List<Todo> getAllTodos() throws IOException {
        List<Todo> todos = new ArrayList<>();

        if (!Files.exists(Paths.get(fileName))) {
            return todos;
        }

        List<String> lines = Files.readAllLines(Paths.get(fileName));
        for (String line : lines) {
            String[] parts = line.split(",");
            String content = parts[0].trim();
            boolean done = Boolean.parseBoolean(parts[1].trim());
            Todo todo = new Todo(content, done);
            todos.add(todo);
        }

        return todos;
    }
}
