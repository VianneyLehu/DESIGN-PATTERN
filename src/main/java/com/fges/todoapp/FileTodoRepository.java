// FileTodoRepository.java
package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileTodoRepository implements TodoRepository {
    private String fileName;

    public FileTodoRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Todo> getAllTodos() throws IOException {
        Path filePath = Paths.get(fileName);
        String fileContent = "";

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }

        List<Todo> todos = new ArrayList<>();

        if (fileName.endsWith(".json")) {
            // JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(fileContent);
            if (actualObj instanceof MissingNode) {
                // Node was not recognized
                actualObj = mapper.createArrayNode();
            }

            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.forEach(node -> {
                    String content= node.get("content").asText();
                    String author = node.get("author").asText();
                    boolean done = node.get("done").asBoolean();
                    Todo todo = new Todo(content, author, done);
                    todos.add(todo);
                });
            }
        }

        return todos;
    }

    @Override
    public void insertTodo(Todo todo) throws IOException {
        // Read existing content from file
        String fileContent = "";
        Path filePath = Paths.get(fileName);

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }

        // Parse existing content as JSON
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode todosArray = mapper.createArrayNode();

        if (!fileContent.isBlank()) {
            todosArray = (ArrayNode) mapper.readTree(fileContent);
        }

        // Create JSON object for new todo
        ObjectNode todoNode = mapper.createObjectNode();
        todoNode.put("content", todo.getContent());
        todoNode.put("author", todo.getAuthor());
        todoNode.put("done", todo.isDone());

        todosArray.add(todoNode);

        Files.writeString(filePath, todosArray.toPrettyString());
    }

}
