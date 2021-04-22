package ru.geekbrains.java.part3.lesson3.client;

import java.io.*;
import java.net.Socket;

public class ChatCommunication {
    private final DataInputStream in;
    private final DataOutputStream out;

    public File getFile() {
        return file;
    }

    private final File file = new File("C:\\Users\\ivashchenko\\IdeaProjects\\geekbrains-java-homeworks\\src\\ru\\geekbrains\\java\\part3\\lesson3\\client\\rss\\history.txt");

    public ChatCommunication(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during connection establishing.", e);
        }
    }

    public void transmit(String data) {
        try {
            out.writeUTF(data);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during data transmitting.", e);
        }
    }

    public String receive() {
        try (FileWriter fw = new FileWriter(file, true)) {
            String message = in.readUTF();
            fw.write(message + "\n");
            return message;
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during data receiving.", e);
        }
    }
}


