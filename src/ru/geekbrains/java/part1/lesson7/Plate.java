package ru.geekbrains.java.part1.lesson7;

public class Plate {

    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int n) {
        if (food >= n) {
            food -= n;
            return true;
        } else return false;
    }

    public void increaseFood(int n) {
        food += n;
    }

    public void info() {
        System.out.printf("plate contains %d food %n", food);
    }

}
