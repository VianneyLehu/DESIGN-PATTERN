package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public class FileTodoRepositoryJson implements TodoRepository {
    private String fileName;
    private FileTodoReaderJson reader;
    private FileTodoWriterJson writer;

    public FileTodoRepositoryJson(String fileName) {
        this.fileName = fileName;
        this.reader = new FileTodoReaderJson(fileName);
        this.writer = new FileTodoWriterJson(fileName);
    }

    @Override
    public List<Todo> getAllTodos() throws IOException {
        return reader.getAllTodos();
    }

    @Override
    public void insertTodo(Todo todo) throws IOException {
        writer.insertTodo(todo);
    }
}
