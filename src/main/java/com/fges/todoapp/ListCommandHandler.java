package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public class ListCommandHandler implements CommandHandler {
    @Override
    public void handle(String[] args, TodoRepository todoRepository, boolean isDone, String author) throws IOException {
        List<Todo> todos = todoRepository.getAllTodos();
        for (Todo todo : todos) {
            if (isDone && todo.isDone()) {
                System.out.println("Author: " + todo.getAuthor());
                System.out.println("- " + todo.getContent());
            } else if (!isDone && !todo.isDone()) {
                System.out.println("Author: " + todo.getAuthor());
                System.out.println("- " + todo.getContent());
            }
        }
    }
}
