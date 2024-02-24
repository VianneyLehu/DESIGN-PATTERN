package com.fges.todoapp;

import java.io.IOException;

public class MigrationHandler {

    public static void migrate(String sourceFileName, String outputFileName) throws IOException {
        String sourceExtension = getFileExtension(sourceFileName);
        String outputExtension = getFileExtension(outputFileName);

        switch (sourceExtension) {
            case "json":
                handleJsonMigration(sourceFileName, outputFileName, outputExtension);
                break;
            case "csv":
                handleCsvMigration(sourceFileName, outputFileName, outputExtension);
                break;
            default:
                System.err.println("Unsupported source file format.");
                break;
        }
    }

    private static void handleJsonMigration(String sourceFileName, String outputFileName, String outputExtension) throws IOException {
        switch (outputExtension) {
            case "json":
                MigrateJsonToJson.migrate(sourceFileName, outputFileName);
                break;
            case "csv":
                MigrateJsonToCsv.migrate(sourceFileName, outputFileName);
                break;
            default:
                System.err.println("Unsupported output file format.");
                break;
        }
    }

    private static void handleCsvMigration(String sourceFileName, String outputFileName, String outputExtension) throws IOException {
        switch (outputExtension) {
            case "csv":
                MigrateCsvToCsv.migrate(sourceFileName, outputFileName);
                break;
            case "json":
                MigrateCsvToJson.migrate(sourceFileName, outputFileName);
                break;
            default:
                System.err.println("Unsupported output file format.");
                break;
        }
    }

    private static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1);
    }
}
