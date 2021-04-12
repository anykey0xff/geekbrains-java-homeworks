package ru.geekbrains.java.part3.lesson1.fruits;

import java.util.ArrayList;

public class Box<FruitType extends Fruit> {
    private ArrayList<FruitType> storage;
}
