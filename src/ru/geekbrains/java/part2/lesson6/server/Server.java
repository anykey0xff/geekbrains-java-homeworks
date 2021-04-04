package ru.geekbrains.java.part2.lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public Server() {
        try {
            System.out.println("Server ready...");
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket accept = serverSocket.accept();
            Scanner scanner = new Scanner(System.in);

            DataInputStream in = new DataInputStream(accept.getInputStream());
            DataOutputStream out = new DataOutputStream(accept.getOutputStream());

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
                    System.out.println("Client message: " + message);
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
