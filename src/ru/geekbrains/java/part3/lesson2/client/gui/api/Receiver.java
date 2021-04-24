package ru.geekbrains.java.part3.lesson2.client.gui.api;

@FunctionalInterface
public interface Receiver {
    void receive(String data);
}
