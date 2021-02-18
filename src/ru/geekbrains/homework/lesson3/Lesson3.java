package ru.geekbrains.homework.lesson3;

import java.util.Random;
import java.util.Scanner;

/**
 * 1. Написать программу, которая загадывает случайное число от 0 до 9
 * и пользователю дается 3 попытки угадать это число.
 * При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное, или меньше.
 * После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
 * <p>
 * 2. * Создать массив из слов
 * <p>
 * String[] words = {"apple", "orange", "lemon", "banana", "apricot",
 * "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
 * "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
 * "pear", "pepper", "pineapple", "pumpkin", "potato"}.
 * <p>
 * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
 * сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь.
 * Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
 * apple – загаданное
 * apricot - ответ игрока
 * ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
 * Для сравнения двух слов посимвольно можно пользоваться:
 * String str = "apple";
 * char a = str.charAt(0); - метод вернет char, который стоит в слове str на первой позиции
 * Играем до тех пор, пока игрок не отгадает слово.
 * Используем только маленькие буквы.
 */

public class Lesson3 {

    static int bound = 10;              // Граница загадываемого число [0, N) для игры угадай число
    static int rounds = 3;              // Количество попыток для игры угадай число
    static int maxSymbolsCount = 15;    // Количество сравниваемых символов для игры угадай слово
    static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
            "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear",
            "pepper", "pineapple", "pumpkin", "potato"};  // Список слов для игры угадай слово

    public static void main(String[] args) {
        String welcomeMassage = """
                "Введите номер задания
                1. Игра "Угадай число".
                2. Игра "Угадай слово".
                """;
        System.out.println(welcomeMassage);
        String userAnswer = getUserAnswer();
        switch (userAnswer) {
            case "1" -> doTask01();
            case "2" -> doTask02();
            default -> System.out.println("Неправильный номер задания.");
        }
    }

    private static void doTask01() {
        startNumbersGame();
    }

    private static String getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private static void startNumbersGame() {
        String welcomeMassage = """
                ------------------------------------------------------------------------------------------------------
                Игра загадывает случайное число от 0 до %d. У вас есть %d попытки угадать это число.
                При каждой попытке игра сообщает, больше ли указанное число, чем загаданное, или меньше.
                ------------------------------------------------------------------------------------------------------
                """;
        System.out.printf(welcomeMassage, bound - 1, rounds);
        int requiredNumber = getRandomNumber(bound);
        boolean userWin = false;
        for (int round = 0; round < rounds; round++) {
            boolean correctAnswer = playNumbersRound(round, requiredNumber);
            if (correctAnswer) {
                userWin = true;
                break;
            }
        }
        if (userWin) System.out.println("Поздравляем, Вы угадали!");
        else System.out.printf("К сожалению, Вы не справились. Правильный ответ был %d.%n", requiredNumber);
        if (checkRepeatGame()) startNumbersGame();
    }

    private static boolean playNumbersRound(int round, int requiredNumber) {
        System.out.println();
        String messageRound = "Попытка %d. Введите число: ";
        System.out.printf(messageRound, round);
        String userAnswer = getUserAnswer();
        int userNumber;
        try {
            userNumber = Integer.parseInt(userAnswer);
        } catch (NumberFormatException e) {
            System.out.println("Это вообще не число!");
            return false;
        }
        if (userNumber > requiredNumber) {
            System.out.println("Загаданное число меньше.");
        } else if (userNumber < requiredNumber)
            System.out.println("Загаданное число больше.");
        return userNumber == requiredNumber;
    }

    private static int getRandomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }

    private static boolean checkRepeatGame() {
        System.out.println();
        String repeatingMessage = "Повторить игру еще раз? 1 – да / 0 – нет";
        while (true) {
            System.out.println(repeatingMessage);
            String userAnswer = getUserAnswer();
            switch (userAnswer) {
                case "0" -> {
                    return false;
                }
                case "1" -> {
                    return true;
                }
            }
        }
    }

    private static void doTask02() {
        startWordsGame();
    }

    private static void startWordsGame() {
        String welcomeMassage = """
                ------------------------------------------------------------------------------------------------------
                Игра загадывает слово. Вам нужно угадать это слово.
                Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
                Количество попыток неограничено.
                ------------------------------------------------------------------------------------------------------
                """;
        System.out.print(welcomeMassage);
        String requiredWord = words[getRandomNumber(words.length)];
        boolean userWin = false;
        do {
            if (playWordsRound(requiredWord)) userWin = true;
        } while (!userWin);
        System.out.println("Поздравляем, Вы угадали!");
        if (checkRepeatGame()) startWordsGame();
    }

    private static boolean playWordsRound(String requiredWord) {
        System.out.println();
        System.out.print("Введите слово: ");
        String userAnswer = getUserAnswer();

        if (!requiredWord.equals(userAnswer)) {
            doCharByCharComparison(requiredWord, userAnswer);
        }
        return requiredWord.equals(userAnswer);
    }

    private static void doCharByCharComparison(String requiredWord, String userAnswer) {
        for (int i = 0; i < maxSymbolsCount; i++) {
            if (i >= requiredWord.length() || i >= userAnswer.length()) {
                System.out.print("#");
                continue;
            }
            char requiredWordSymbol = requiredWord.charAt(i);
            char userAnswerSymbol = userAnswer.charAt(i);
            if (requiredWordSymbol == userAnswerSymbol) System.out.print(requiredWordSymbol);
            else System.out.print("#");
        }
    }
}