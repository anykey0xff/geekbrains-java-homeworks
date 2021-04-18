package ru.geekbrains.java.part3.lesson2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Optional;

public class ClientHandler {
    private final DataInputStream in;
    private final DataOutputStream out;
    private final ChatServer chatServer;
    private String name;

    public ClientHandler(Socket socket, ChatServer chatServer) {
        this.chatServer = chatServer;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new ChatServerException("Something went wrong during client establishing.", e);
        }

        new Thread(() -> {

            try {
                socket.setSoTimeout(120_000); // даем пользователю 120 секунд, чтобы сделать попытку авторизации.
                doAuthentication();
            } catch (SocketException e) {
                e.printStackTrace();
            } finally {
                if (name == null) {
                    sendMessage("Time out authorization. Session was closed.");
                }
            }

            try {
                socket.setSoTimeout(0);
                listen();
            } catch (SocketException e) {
                e.printStackTrace();
            }

        })
                .start();

    }

    public String getName() {
        return name;
    }

    private void listen() {
        receiveMessage();
    }

    private void doAuthentication() {
        sendMessage("Welcome! Please do authentication.\n"
            + "Please use command: -auth your_login your_pass"
        );
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = in.readUTF();
                if (message.startsWith("-auth")) {
                    String[] credentialsStruct = message.split("\\s");
                    String login = credentialsStruct[1];
                    String password = credentialsStruct[2];

                    Optional<AuthenticationService.Entry> mayBeCredentials = chatServer.getAuthenticationService()
                            .findEntryByCredentials(login, password);

                    if (mayBeCredentials.isPresent()) {
                        AuthenticationService.Entry credentials = mayBeCredentials.get();
                        if (!chatServer.isLoggedIn(credentials.getName())) {
                            name = credentials.getName();
                            chatServer.broadcast(String.format("User %s entered the chat", name));
                            chatServer.subscribe(this);
                            sendMessage(String.format("Welcome %s!", credentials.getName()));
                            return;
                        } else {
                            sendMessage(String.format("User with name %s is already logged in", credentials.getName()));
                        }
                    } else {
                        sendMessage("Incorrect login or password.");
                    }
                } else {
                    sendMessage("Incorrect authentication message. " +
                            "Please use valid command: -auth your_login your_pass");
                }
            } catch (IOException e) {
                throw new ChatServerException("Something went wrong during client authentication.", e);
            }
        }
    }

    public void receiveMessage() {
        while (true) {
            try {
                String message = in.readUTF();
                if (message.startsWith("/w")) {
                    String[] messageStruct = message.split("\\s");
                    String clientName = messageStruct[1];
                    StringBuilder messageBuilder = new StringBuilder();
                    for (int i = 2; i < messageStruct.length; i++) {
                        messageBuilder.append(messageStruct[i]).append(" ");
                    }
                    message = String.format("%s whispers: %s", name, messageBuilder);
                    chatServer.whisper(message, clientName);
                } else if (message.startsWith("/quit")) {
                    chatServer.unsubscribe(this);
                    chatServer.broadcast(String.format("User %s left the chat", name));
                    sendMessage("Logged out. Bye.");
                    return;
                } else {
                    chatServer.broadcast(String.format("%s: %s", name, message));
                }
            } catch (IOException e) {
                throw new ChatServerException("Something went wrong during receiving the message.", e);
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new ChatServerException("Something went wrong during sending the message.", e);
        }
    }
}
