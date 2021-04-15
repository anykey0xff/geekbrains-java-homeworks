package ru.geekbrains.java.part2.lesson8.client.gui.api;

@FunctionalInterface
public interface Receiver {
    void receive(String data);
}
