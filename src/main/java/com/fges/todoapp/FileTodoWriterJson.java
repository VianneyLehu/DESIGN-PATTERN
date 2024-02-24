package com.fges.todoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTodoWriterJson {
    private String fileName;

    public FileTodoWriterJson(String fileName) {
        this.fileName = fileName;
    }

    public void insertTodo(Todo todo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode todosArray = mapper.createArrayNode();

        if (Files.exists(Paths.get(fileName))) {
            String fileContent = Files.readString(Paths.get(fileName));
            todosArray = (ArrayNode) mapper.readTree(fileContent);
        }

        ObjectNode todoNode = mapper.createObjectNode();
        todoNode.put("content", todo.getContent());
        todoNode.put("done", todo.isDone());

        todosArray.add(todoNode);

        Files.writeString(Paths.get(fileName), todosArray.toPrettyString());
    }
}
