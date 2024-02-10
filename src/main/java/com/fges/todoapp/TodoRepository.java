// TodoRepository.java
package com.fges.todoapp;

import java.io.IOException;
import java.util.List;

public interface TodoRepository {
    List<Todo> getAllTodos() throws IOException;

    void insertTodo(Todo todo) throws IOException;
}
