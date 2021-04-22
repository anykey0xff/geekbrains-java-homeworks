package ru.geekbrains.java.part3.lesson3.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public class AuthenticationService {

    public Optional<Entry> findEntryByCredentials(String login, String password) throws SQLException {

        try (Connection connection = DBConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE login = ? AND password = ?"
            );
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(
                        new Entry(
                                resultSet.getString("name"),
                                resultSet.getString("login"),
                                resultSet.getString("password")
                        )
                );
            } else return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setNameByCredentials(String newName, String login, String password) {
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET name = ? WHERE login = ? AND password = ?"
            );
            statement.setString(1, newName);
            statement.setString(2, login);
            statement.setString(3, password);

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Entry {
        private final String name;
        private final String login;
        private final String password;

        public Entry(String name, String login, String password) {
            this.name = name;
            this.login = login;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

    }
}
