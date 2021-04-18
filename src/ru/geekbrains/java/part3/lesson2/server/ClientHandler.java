package ru.geekbrains.java.part3.lesson2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.Optional;

public class ClientHandler {
    private final DataInputStream in;
    private final DataOutputStream out;
    private final ChatServer chatServer;
    private String name;
    private String login;
    private String password;

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
                sendMessage("Time out authorization. Session was closed.");
                e.printStackTrace();
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
        while (true) {
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
                            this.login = credentials.getLogin();
                            this.password = credentials.getPassword();
                            chatServer.broadcast(String.format("User %s entered the chat", name));
                            chatServer.subscribe(this);
                            sendMessage(String.format(
                                    "Welcome %s!%nAvailable commands are:%n/q - quit%n/w - whisper%n/c - change name",
                                    credentials.getName()));
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
            } catch (SQLException e) {
                throw new ChatServerException("Something went wrong during connection to data base.", e);
            }
        }
    }

    private void changeName(String newName) throws SQLException {

        chatServer.getAuthenticationService().setNameByCredentials(newName, login, password);
        chatServer.broadcast("User " + name + " changes nickname to " + newName);
        name = newName;

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

                } else if (message.startsWith("/q")) {
                    chatServer.unsubscribe(this);
                    chatServer.broadcast(String.format("User %s left the chat", name));
                    sendMessage("Logged out. Bye.");
                    return;

                } else if (message.startsWith("/c")) {
                    String[] messageStruct = message.split("\\s");
                    String newName = messageStruct[1];
                    changeName(newName);

                } else {
                    chatServer.broadcast(String.format("%s: %s", name, message));

                }
            } catch (IOException | SQLException e) {
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
