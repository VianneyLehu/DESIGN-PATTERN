package com.fges.todoapp;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Hello world!
 */

public class App {

    /**
     * Do not change this method
     */

    private static final Map<String, CommandHandler> commandHandlers = new HashMap<>();

    static {
        commandHandlers.put("insert", new InsertCommandHandler());
        commandHandlers.put("list", new ListCommandHandler());
        // Add more command handlers as needed
    }
    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }
    public static int exec(String[] args) throws IOException {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        cliOptions.addOption("d", "done", false, "Mark the todo as done");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");

        List<String> positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
            return 1;
        }
        String commandName = positionalArgs.get(0);
        CommandHandler handler = commandHandlers.get(commandName);

        if (handler == null) {
            System.err.println("Invalid command");
            return 1;
        }

        // Pass the --done flag to the handler
        boolean isDone = cmd.hasOption("d");
        handler.handle(positionalArgs.toArray(new String[0]), fileName, isDone);
        return 0;
    }

}
