package ru.geekbrains.java.part3.lesson1;

import ru.geekbrains.java.part3.lesson1.fruits.Fruit;

import java.util.ArrayList;

public class Box<FruitType extends Fruit> {
    private final ArrayList<FruitType> storage;

    public Box() {
        this.storage = new ArrayList<>();
    }

    public void put(FruitType fruit) {
        storage.add(fruit);
    }

    public float getWeight() {
        float weight = 0f;
        for (FruitType fruitType : storage) {
            weight += fruitType.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<? extends Fruit> anotherBox) {
        return (getWeight() == anotherBox.getWeight());
    }

    public void pour(Box<FruitType> anotherBox) {
        storage.addAll(anotherBox.storage);
        anotherBox.empty();
    }

    public void empty() {
        storage.clear();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Box [");
        for (FruitType fruitType : storage) {
            if (!stringBuilder.toString().equals("Box [")) stringBuilder.append(", ");
            stringBuilder.append(fruitType.getClass().getSimpleName());
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
