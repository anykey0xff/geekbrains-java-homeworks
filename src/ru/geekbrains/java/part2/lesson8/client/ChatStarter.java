package ru.geekbrains.java.part2.lesson8.client;

public class ChatStarter {
    public static void run() {
        run("localhost", 8080);
    }

    public static void run(String host, int port) {
        new Chat(host, port);
    }
}
