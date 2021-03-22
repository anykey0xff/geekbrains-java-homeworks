package ru.geekbrains.java.part2.lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Phonebook {
    private final Map<String, Set<String>> book;

    public Phonebook() {
        this.book = new HashMap<>();
    }

    public void add(String name, String number) {
        if (book.containsKey(name)) {
            book.get(name).add(number);
        } else {
            Set<String> numbers = new HashSet<>();
            numbers.add(number);
            book.put(name, numbers);
        }
    }

    public Set<String> get(String name) {
        return book.get(name);
    }

}
