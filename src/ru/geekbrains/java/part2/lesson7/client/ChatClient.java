package ru.geekbrains.java.part2.lesson7.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public ChatClient() {
        try {
            Socket socket = new Socket("localhost", 8080);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    try {
                        out.writeUTF(scanner.nextLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            })
                    .start();

            while (true) {
                System.out.println(in.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
