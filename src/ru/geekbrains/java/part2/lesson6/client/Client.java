package ru.geekbrains.java.part2.lesson6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 1. Написать консольный вариант клиент\серверного приложения,
 * в котором пользователь может писать сообщения, как на клиентской стороне,
 * так и на серверной. Т.е. если на клиентской стороне написать "Привет",
 * нажать Enter то сообщение должно передаться на сервер и там отпечататься в консоли.
 * Если сделать то же самое на серверной стороне, сообщение соответственно
 * передается клиенту и печатается у него в консоли. Есть одна особенность,
 * которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд,
 * такую ситуацию необходимо корректно обработать
 *
 * Разобраться с кодом с занятия, он является фундаментом проекта-чата
 * ВАЖНО! Сервер общается только с одним клиентом,
 * т.е. не нужно запускать цикл, который будет ожидать второго/третьего/n-го клиентов
 */

public class Client {
    public Client() {

        try {
            System.out.println("Client ready...");
            Socket socket = new Socket("127.0.0.1", 8080);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            new Thread(() -> {
                while (true) {
                    try {
                        out.writeUTF(scanner.nextLine());
                    } catch (IOException e) {
                        System.out.println("Connection closed.");
                        break;
                    }
                }
            })
                    .start();

            while (true) {
                try {
                    String message = in.readUTF();
                    System.out.println("Server message: " + message);
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
