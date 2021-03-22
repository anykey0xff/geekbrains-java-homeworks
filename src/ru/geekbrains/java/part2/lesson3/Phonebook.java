package ru.geekbrains.java.part2.lesson3;

import java.util.*;

public class Phonebook {
    private final Map<String, Set<String>> book;

    public Phonebook() {
        this.book = new HashMap<>();
    }

    public void add(String name, String number) {
        Set<String> numbers = book.getOrDefault(name, new HashSet<>());
        numbers.add(number);
        book.putIfAbsent(name, numbers);
    }

    public Set<String> get(String name) {
        return book.getOrDefault(name, Collections.emptySet());
    }

}
