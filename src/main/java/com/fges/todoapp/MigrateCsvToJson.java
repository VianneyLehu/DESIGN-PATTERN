package com.fges.todoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MigrateCsvToJson {
    public static void migrate(String sourceCsvFileName, String outputJsonFileName) throws IOException {
        // Read from CSV using FileTodoReaderCsv
        List<Todo> todos = readTodosFromCsv(sourceCsvFileName);

        // Write to JSON file
        writeTodosToJsonFile(todos, outputJsonFileName);
        System.out.println("Done.");

    }

    private static List<Todo> readTodosFromCsv(String csvFileName) throws IOException {
        FileTodoReaderCsv reader = new FileTodoReaderCsv(csvFileName);
        return reader.getAllTodos();
    }

    private static void writeTodosToJsonFile(List<Todo> todos, String outputJsonFileName) throws IOException {
        // Create ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        // Create ArrayNode for todos
        ArrayNode todosArray = mapper.createArrayNode();

        // Iterate through todos and add to ArrayNode
        for (Todo todo : todos) {
            ObjectNode todoNode = mapper.createObjectNode();
            todoNode.put("content", todo.getContent());
            todoNode.put("done", todo.isDone());
            todosArray.add(todoNode);
        }

        // Write todos to JSON file
        mapper.writerWithDefaultPrettyPrinter().writeValue(new FileWriter(outputJsonFileName), todosArray);
    }
}
