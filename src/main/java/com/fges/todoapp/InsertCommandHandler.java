package com.fges.todoapp;

import java.io.IOException;

public class InsertCommandHandler implements CommandHandler {
    @Override
    public void handle(String[] args, TodoRepository todoRepository, boolean isDone) throws IOException {
        if (args.length < 2) {
            System.err.println("Missing TODO name");
            return;
        }
        String todoName = args[1];
        Todo todo = new Todo(todoName, isDone);
        todoRepository.insertTodo(todo);
    }
}
