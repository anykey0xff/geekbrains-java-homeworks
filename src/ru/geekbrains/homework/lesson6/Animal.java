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
        String fullName = this.getClass().getSimpleName() + " " + name;
        System.out.printf("Trying to get %s run %dm... %n", fullName, distance);
        move(distance, maxRunDistance);
    }

    public void swim(int distance) {
        String fullName = this.getClass().getSimpleName() + " " + name;
        System.out.printf("Trying to get %s swim %dm... %n", fullName, distance);
        move(distance, maxSwimDistance);
    }

    private void move(int distance, int maxDistance) {
        String fullName = this.getClass().getSimpleName() + " " + name;
        if (maxDistance == 0 || distance <= 0) {
            System.out.printf("%s can't do this action. %n", fullName);
        } else if (distance >= maxDistance) {
            System.out.printf("The distance is too far! %s can move no more then %dm. %n", fullName, maxDistance);
        } else {
            System.out.printf("%s moved %dm. %n", fullName, distance);
        }
    }

}
