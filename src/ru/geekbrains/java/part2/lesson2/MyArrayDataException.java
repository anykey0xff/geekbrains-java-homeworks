package ru.geekbrains.java.part2.lesson2;

public class MyArrayDataException extends RuntimeException{

    public MyArrayDataException(String s) {
        super(s);
    }

    public MyArrayDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
