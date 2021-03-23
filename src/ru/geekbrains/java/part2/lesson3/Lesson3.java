package ru.geekbrains.java.part2.lesson3;

import java.util.*;

/**
 * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
 * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 * Посчитать сколько раз встречается каждое слово.
 * <p>
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи.
 * С помощью метода get() искать номер телефона по фамилии. Следует учесть,
 * что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 * тогда при запросе такой фамилии должны выводиться все телефоны.
 * <p>
 * Желательно как можно меньше добавлять своего, чего нет в задании
 * (т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес),
 * делать взаимодействие с пользователем через консоль и т.д.).
 * Консоль желательно не использовать (в том числе Scanner),
 * тестировать просто из метода main() прописывая add() и get().
 */

public class Lesson3 {
    public static void main(String[] args) {
        doTask01();
        doTask02();
    }

    public static void doTask01() {
        String[] words = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Zero"};

        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        System.out.println(uniqueWords);

        Map<String, Integer> countedWords = new HashMap<>();
        for (String word : words) {
            if (countedWords.containsKey(word)) {
                countedWords.put(word, countedWords.get(word) + 1);
            } else {
                {
                    countedWords.put(word, 1);
                }
            }
        }
        System.out.println(countedWords);
    }

    public static void doTask02() {
        Phonebook phonebook = new Phonebook();
        phonebook.add("John", "+7-999-999-99-99");
        phonebook.add("John", "+7-999-999-99-98");
        phonebook.add("Karl", "+7-999-999-99-97");

        System.out.println("John: " + phonebook.get("John"));
        System.out.println("Karl: " + phonebook.get("Karl"));
        System.out.println("Lana: " + phonebook.get("Lana"));
    }
}
