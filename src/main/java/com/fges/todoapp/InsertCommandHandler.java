package com.fges.todoapp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InsertCommandHandler implements CommandHandler {
    @Override
    public void handle(String[] args, String fileName, boolean isDone) throws IOException {
        if (args.length < 2) {
            System.err.println("Missing TODO name");
            return;
        }
        String todo = args[1];
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
                actualObj = JsonNodeFactory.instance.arrayNode();
            }

            if (actualObj instanceof ArrayNode arrayNode) {
                ObjectNode todoNode = mapper.createObjectNode();
                todoNode.put("todo", todo);
                if (isDone) {
                    todoNode.put("done", true);
                }
                arrayNode.add(todoNode);
            }

            Files.writeString(filePath, actualObj.toString());
        }
    }
}

