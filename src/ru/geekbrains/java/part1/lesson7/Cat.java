package ru.geekbrains.java.part1.lesson7;

public class Cat {
    private final String name;
    private final int appetite;
    private boolean isHungry = true;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate plate) {
        if (isHungry) {
            isHungry = !plate.decreaseFood(appetite);
        }
    }

    public void info() {
        String isNot = "is";
        if (!isHungry)
            isNot = "is not";
        System.out.printf("cat %s %s hungry. %n", name, isNot);
    }
}
