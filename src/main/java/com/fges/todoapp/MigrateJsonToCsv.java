package com.fges.todoapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MigrateJsonToCsv {
    public static void migrate(String sourceJsonFileName, String outputCsvFileName) throws IOException {
        // Read from JSON using FileTodoReaderJson
        List<Todo> todos = readTodosFromJson(sourceJsonFileName);

        // Write to CSV file
        writeTodosToCsvFile(todos, outputCsvFileName);


    }

    private static List<Todo> readTodosFromJson(String jsonFileName) throws IOException {
        FileTodoReaderJson reader = new FileTodoReaderJson(jsonFileName);
        return reader.getAllTodos();
    }

    private static void writeTodosToCsvFile(List<Todo> todos, String outputCsvFileName) throws IOException {
        // Create a FileWriter object for the output CSV file
        FileWriter writer = new FileWriter(outputCsvFileName);

        // Write CSV header
        writer.write("Content,Done\n");

        // Write todos to CSV
        for (Todo todo : todos) {
            writer.write(todo.getContent() + "," + todo.isDone() + "\n");
        }

        // Close the FileWriter
        writer.close();
    }
}
