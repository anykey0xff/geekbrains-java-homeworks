package ru.geekbrains.java.part3.lesson2.server;

public class ChatServerException extends RuntimeException {
    public ChatServerException(String message) {
        super(message);
    }

    public ChatServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
