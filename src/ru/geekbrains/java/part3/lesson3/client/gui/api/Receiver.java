package ru.geekbrains.java.part3.lesson3.client.gui.api;

@FunctionalInterface
public interface Receiver {
    void receive(String data);
}
