package ru.geekbrains.java.part3.lesson3.client;

import ru.geekbrains.java.part3.lesson3.client.gui.ChatFrame;
import ru.geekbrains.java.part3.lesson3.client.gui.api.Receiver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Chat {
    private final ChatFrame frame;
    private final ChatCommunication communication;

    public Chat(String host, int port) {
        communication = new ChatCommunication(host, port);
        frame = new ChatFrame(communication::transmit);

        new Thread(() -> {
            Receiver receiver = frame.getReceiver();

            try (BufferedReader br = new BufferedReader(new FileReader(communication.getFile()))) {
                String msg;
                int i = 0;
                while ((msg = br.readLine()) != null && i <= 100) {
                    receiver.receive(msg);
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                String msg = communication.receive();
                if (!msg.isBlank()) {
                    receiver.receive(msg);
                }
            }
        })
                .start();
    }

}
