package com.fges.todoapp;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.*;

public class App {

    private static final Map<String, CommandHandler> commandHandlers = new HashMap<>();

    static {
        commandHandlers.put("insert", new InsertCommandHandler());
        commandHandlers.put("list", new ListCommandHandler());
        commandHandlers.put("migrate", new MigrationCommandHandler());

        // Add more command handlers as needed
    }

    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    public static int exec(String[] args) throws IOException {
        Options cliOptions = new Options();
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        cliOptions.addOption("d", "done", false, "Mark the todo as done");
        cliOptions.addOption("o", "output", true, "Output file for migration");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return 1;
        }

        String fileName = cmd.getOptionValue("s");

        if (cmd.hasOption("o")) {
            String outputFileName = cmd.getOptionValue("o");
            try {
                MigrationHandler.migrate(fileName, outputFileName);
                System.out.println("Migration completed successfully.");
                return 0;
            } catch (IOException e) {
                System.err.println("Error occurred during migration: " + e.getMessage());
                return 1;
            }
        }


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
        FileHandler fileHandler = new FileHandler(fileName);
        TodoRepository todoRepository = fileHandler.getFileRepository();
        handler.handle(positionalArgs.toArray(new String[0]), todoRepository, isDone);
        return 0;
    }
}
