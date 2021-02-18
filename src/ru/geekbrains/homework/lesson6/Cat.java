package ru.geekbrains.homework.lesson6;

public class Cat extends Animal {

    private static final int maxRunDistance = 200;
    private static final int maxSwimDistance = 0;
    private static int catCount = 0;

    public Cat(String name) {
        super(name, maxRunDistance, maxSwimDistance);
        System.out.printf("New cat initialized. Its name is %s. %n", name);
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

}
