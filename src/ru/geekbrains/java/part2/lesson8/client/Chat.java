package ru.geekbrains.java.part2.lesson8.client;

import ru.geekbrains.java.part2.lesson8.client.gui.ChatFrame;
import ru.geekbrains.java.part2.lesson8.client.gui.api.Receiver;

public class Chat {
    private final ChatFrame frame;
    private final ChatCommunication communication;

    public Chat(String host, int port) {
        communication = new ChatCommunication(host, port);
        frame = new ChatFrame(data -> communication.transmit(data));

        new Thread(() -> {
            Receiver receiver = frame.getReceiver();
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
