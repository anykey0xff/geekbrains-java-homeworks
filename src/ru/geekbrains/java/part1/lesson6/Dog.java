package ru.geekbrains.java.part1.lesson6;

public class Dog extends Animal {

    private static final int maxRunDistance = 500;
    private static final int maxSwimDistance = 10;
    private static int dogCount = 0;

    public Dog(String name) {
        super(name, maxRunDistance, maxSwimDistance);
        System.out.printf("New dog initialized. Its name is %s. %n", name);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

}
