package ru.geekbrains.homework.lesson6;

public abstract class Animal {

    private static int animalCount = 0;

    private final int maxSwimDistance;
    private final int maxRunDistance;
    private final String name;

    public Animal(String name, int maxRunDistance, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        animalCount++;
    }

    public static int getAnimalCount() {
        return animalCount;
    }

    public void run(int distance) {
        System.out.printf("Trying to get %s run %dm... %n", name, distance);
        move(distance, maxRunDistance);
    }

    public void swim(int distance) {
        System.out.printf("Trying to get %s swim %dm... %n", name, distance);
        move(distance, maxSwimDistance);
    }

    private void move(int distance, int maxDistance) {
        if (maxDistance == 0 || distance <= 0) {
            System.out.printf("%s can't do this action. %n", name);
        } else if (distance >= maxDistance) {
            System.out.printf("The distance is too far! %s can move no more then %dm. %n", name, maxDistance);
        } else {
            System.out.printf("%s moved %dm. %n", name, distance);
        }
    }

}
