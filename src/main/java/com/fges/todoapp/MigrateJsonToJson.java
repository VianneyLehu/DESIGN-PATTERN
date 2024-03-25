package com.fges.todoapp;

import com.fges.todoapp.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MigrateJsonToJson {
    public static void migrate(String sourceJsonFileName, String outputJsonFileName) throws IOException {
        // Read from JSON using FileTodoReaderJson
        List<Todo> todos = readTodosFromJson(sourceJsonFileName);

        // Write to JSON file
        writeTodosToJsonFile(todos, outputJsonFileName);


    }

    private static List<Todo> readTodosFromJson(String jsonFileName) throws IOException {
        FileTodoReaderJson reader = new FileTodoReaderJson(jsonFileName);
        return reader.getAllTodos();
    }

    private static void writeTodosToJsonFile(List<Todo> todos, String outputJsonFileName) throws IOException {
        // Create a FileWriter object for the output JSON file
        FileWriter writer = new FileWriter(outputJsonFileName);

        // Start writing the JSON array
        writer.write("[");

        // Iterate through the list of todos
        for (int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            writer.write("{");
            writer.write("\"content\":\"" + todo.getContent() + "\",");
            writer.write("\"done\":" + todo.isDone());
            writer.write("}");

            // If it's not the last todo, add a comma
            if (i < todos.size() - 1) {
                writer.write(",");
            }
        }

        // Close the JSON array
        writer.write("]");

        // Close the FileWriter
        writer.close();
    }
}
