package com.fges.todoapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MigrateCsvToCsv {
    public static void migrate(String sourceCsvFileName, String outputCsvFileName) throws IOException {
        // Read from CSV using FileTodoReaderCsv
        List<Todo> todos = readTodosFromCsv(sourceCsvFileName);

        // Write to CSV file
        writeTodosToCsvFile(todos, outputCsvFileName);

    }

    private static List<Todo> readTodosFromCsv(String csvFileName) throws IOException {
        FileTodoReaderCsv reader = new FileTodoReaderCsv(csvFileName);
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

