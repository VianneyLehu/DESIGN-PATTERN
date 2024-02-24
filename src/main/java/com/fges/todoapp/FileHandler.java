package com.fges.todoapp;

public class FileHandler {
    private String fileName;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public TodoRepository getFileRepository() {
        String fileExtension = getFileExtension(fileName);

        if (fileExtension.equals("json")) {
            return new  FileTodoRepositoryJson(fileName);
        } else if (fileExtension.equals("csv")) {
            return new FileTodoRepositoryCsv(fileName);
        } else {
            throw new IllegalArgumentException("Unsupported file format.");
        }
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1);
    }
}
