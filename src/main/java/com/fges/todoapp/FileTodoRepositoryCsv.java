package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public class FileTodoRepositoryCsv implements TodoRepository {
    private String fileName;
    private FileTodoReaderCsv reader;
    private FileTodoWriterCsv writer;

    public FileTodoRepositoryCsv(String fileName) {
        this.fileName = fileName;
        this.reader = new FileTodoReaderCsv(fileName);
        this.writer = new FileTodoWriterCsv(fileName);
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
