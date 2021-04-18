package ru.geekbrains.java.part3.lesson2.client;

public class ChatStarter {
    public static void run() {
        run("localhost", 8080);
    }

    public static void run(String host, int port) {
        new Chat(host, port);
    }
}
