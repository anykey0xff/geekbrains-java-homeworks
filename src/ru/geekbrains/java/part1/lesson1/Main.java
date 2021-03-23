package ru.geekbrains.java.part1.lesson1;

public class Main {

    public static void main(String[] args) {
        doTask01();
        doTask02();
        doTask03();
        doTask04();
        doTask05();
        doTask06();
        doTask07();
        doTask08();
    }

    private static void doTask01() {
        System.out.println("Задание 1 выполнено!");
    }

    private static void doTask02() {
        byte number1 = 127;
        short number2 = 32767;
        int number3 = 2147483647;
        long number4 = 9223372036854775807L;
        float number5 = 3.4E38F;
        double number6 = 1.7E308D;
        char character = '%';
        boolean logicalVariable = true;
        String stringVariable = "Задание 2 выполнено!";
        System.out.println(stringVariable);
    }

    private static void doTask03() {
        float result = doCalculationABCD(1.0F, 2.0F, 3.0F, 4.0F);
        System.out.println(result);
    }

    private static float doCalculationABCD(float a, float b, float c, float d) {
        if (d != 0.0F) {
            return a * (b + c / d);
        } else {
            System.out.println("Division by zero");
            return 0.0F;
        }
    }

    private static void doTask04() {
        boolean isSumBetween10and20 = checkIsSumBetween10and20(1, 2);
        System.out.println(isSumBetween10and20);
    }

    private static boolean checkIsSumBetween10and20(int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    private static void doTask05() {
        writeIsNumberPositive(0);
    }

    private static void writeIsNumberPositive(int a) {
        if (a >= 0) {
            System.out.println("Number is positive");
        } else {
            System.out.println("Number is negative");
        }

    }

    private static void doTask06() {
        boolean isNumberNegative = checkIsNumberNegative(0);
        System.out.println(isNumberNegative);
    }

    private static boolean checkIsNumberNegative(int a) {
        return a < 0;
    }

    private static void doTask07() {
        greetUserName("Андрей");
    }

    private static void greetUserName(String userName) {
        System.out.println("Привет, " + userName + "!");
    }

    private static void doTask08() {
        writeIsLeapYear(1900);
    }

    private static void writeIsLeapYear(int year) {
        if ((year % 4 != 0 || year % 100 == 0) && year % 400 != 0) {
            System.out.println(year + " - это не високосный год");
        } else {
            System.out.println(year + " - это високосный год");
        }

    }

}
