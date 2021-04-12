package ru.geekbrains.java.part3.lesson1;

import ru.geekbrains.java.part3.lesson1.fruits.Fruit;

import java.util.ArrayList;

public class Box<FruitType extends Fruit> {
    private ArrayList<FruitType> storage;

    public Box() {
        this.storage = new ArrayList<>();
    }

    public void put (FruitType fruit) {
        storage.add(fruit);
    }

    public float getWeight () {
        float weight = 0f;
        for (FruitType fruitType : storage) {
            weight += fruitType.getWeight();
        }
        return weight;
    }

    public boolean compare (Box<? extends Fruit> anotherBox) {
        return (this.getWeight() == anotherBox.getWeight());
    }
}
