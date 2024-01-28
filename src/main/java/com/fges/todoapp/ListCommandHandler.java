package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ListCommandHandler implements CommandHandler {
    @Override
    public void handle(String[] args, String fileName) throws IOException {
        Path filePath = Paths.get(fileName);
        String fileContent = "";

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }

        if (fileName.endsWith(".json")) {
            // JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(fileContent);
            if (actualObj instanceof MissingNode) {
                // Node was not recognized
                actualObj = mapper.createArrayNode();
            }

            if (actualObj instanceof ArrayNode arrayNode) {
                arrayNode.forEach(node -> System.out.println("- " + node.toString()));
            }
        } else if (fileName.endsWith(".csv")) {
            // CSV
            System.out.println(fileContent.replaceAll("\n", "\n- "));
        } else {
            System.err.println("Unsupported file format");
        }
    }
}