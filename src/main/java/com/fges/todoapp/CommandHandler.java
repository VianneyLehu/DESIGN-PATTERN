package com.fges.todoapp;
import org.apache.commons.cli.CommandLine;

import java.io.IOException;

public interface CommandHandler {

    void handle(String[] args, TodoRepository repository, boolean isDone) throws IOException;

    default void handleMigration(CommandLine cmd, TodoRepository repository) throws IOException {
        throw new UnsupportedOperationException("Migration command not supported.");
    }
}
