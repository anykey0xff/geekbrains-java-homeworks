package ru.geekbrains.java.part1.lesson6;

/**
 * 1. Создать классы Собака и Кот с наследованием от класса Животное.
 *
 * 2. Все животные могут бежать и плыть. В качестве параметра каждому методу передается длина препятствия.
 * Результатом выполнения действия будет печать в консоль.
 * (Например, dogBobik.run(150); -> 'Бобик пробежал 150 м.');
 *
 * 3. У каждого животного есть ограничения на действия
 * (бег: кот 200 м., собака 500 м.; плавание: кот не умеет плавать, собака 10 м.).
 *
 * 4. * Добавить подсчет созданных котов, собак и животных.
 */

public class Main {
    public static void main(String[] args) {
        doTasks();
    }

    private static void doTasks() {

        Cat catTom = new Cat("Tom");
        Dog dogRex = new Dog("Rex");
        System.out.println();

        catTom.run(150);
        System.out.println();

        dogRex.run(600);
        System.out.println();

        catTom.swim(10);
        System.out.println();

        dogRex.swim(-200);
        System.out.println();

        System.out.printf(
                "Counts: %n cats - %d, %n dogs - %d, %n animals - %d %n",
                Cat.getCatCount(),
                Dog.getDogCount(),
                Animal.getAnimalCount()
        );

    }

}
