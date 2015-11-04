package ru.javawebinar.webapp.exceptions;

public enum ExceptionType {
    XML_ERROR("Xml error"),
    SERIALISATION_ERROR("Error jf serialisation"),
    IO_ERROR("Input/Output error"),
    NOT_FOUND("Resume not found in storage"),
    ALREADY_EXISTS ("Resume is already exist in storage"),
    MAX_VOLUME_EXCEEDED ("Max storage volume is exceeded");

    private String message;

    ExceptionType(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
