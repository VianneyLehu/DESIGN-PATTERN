package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileTodoReaderJson {
    private String fileName;

    public FileTodoReaderJson(String fileName) {
        this.fileName = fileName;
    }

    public List<Todo> getAllTodos() throws IOException {
        List<Todo> todos = new ArrayList<>();
        String fileContent = "";

        if (Files   .exists(Paths.get(fileName))) {
            fileContent = Files.readString(Paths.get(fileName));
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);

        if (actualObj instanceof MissingNode) {
            // If the file is empty or not valid, return an empty list
            return todos;
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.forEach(node -> {
                String content = node.get("content").asText();
                boolean done = node.get("done").asBoolean();
                Todo todo = new Todo(content, done);
                todos.add(todo);
            });
        }

        return todos;
    }
}
