package com.fges.todoapp;

import org.apache.commons.cli.CommandLine;

import java.io.IOException;

public class MigrationCommandHandler implements CommandHandler {

    @Override
    public void handle(String[] args, TodoRepository repository, boolean isDone) throws IOException {
        throw new UnsupportedOperationException("Regular command handling not supported for migration.");
    }

    @Override
    public void handleMigration(CommandLine cmd, TodoRepository repository) throws IOException {
        String sourceFileName = cmd.getOptionValue("s");
        String outputFileName = cmd.getOptionValue("o");

        if (sourceFileName == null || outputFileName == null) {
            System.err.println("Missing source or output file name.");
            return;
        }

        try {
            MigrationHandler.migrate(sourceFileName, outputFileName);
            System.out.println("Migration completed successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred during migration: " + e.getMessage());
        }
    }
}
