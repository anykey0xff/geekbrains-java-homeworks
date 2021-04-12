package ru.geekbrains.java.part3.lesson1;

import ru.geekbrains.java.part3.lesson1.fruits.Apple;
import ru.geekbrains.java.part3.lesson1.fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 1. Написать метод, который меняет два элемента массива местами.
 * (массив может быть любого ссылочного типа);
 * <p>
 * 2. Написать метод, который преобразует массив в ArrayList;
 * <p>
 * 3. Большая задача:
 * <p>
 * a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
 * <p>
 * b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
 * поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 * <p>
 * c. Для хранения фруктов внутри коробки можете использовать ArrayList;
 * <p>
 * d. Сделать метод getWeight() который высчитывает вес коробки,
 * зная количество фруктов и вес одного фрукта
 * (вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
 * <p>
 * e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
 * которую подадут в compare в качестве параметра, true - если их веса равны,
 * false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
 * <p>
 * f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
 * (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
 * соответственно в текущей коробке фруктов не остается,
 * а в другую перекидываются объекты, которые были в этой коробке;
 * <p>
 * g. Не забываем про метод добавления фрукта в коробку.
 */

public class Main {
    public static void main(String[] args) {
        doTask01();
        doTask02();
        doTask03();
    }

    private static void doTask01() {
        System.out.printf("%nTask 01 %n");
        String[] strArr = {"abc", "zxy"};
        System.out.println(Arrays.toString(strArr));
        ElementsSwapper<String> integerElementsSwapper = new ElementsSwapper<>();
        integerElementsSwapper.swap(strArr, 0, 1);
        System.out.println(Arrays.toString(strArr));
    }

    private static void doTask02() {
        System.out.printf("%nTask 02 %n");
        String[] strArr = {"abc", "zxy"};
        System.out.println(strArr.getClass().getSimpleName() + ": " + Arrays.toString(strArr));
        ArrayToListConverter<String> stringArrayToListConverter = new ArrayToListConverter<>();
        ArrayList<String> srtArrList = stringArrayToListConverter.convert(strArr);
        System.out.println(srtArrList.getClass().getSimpleName() + ": " + srtArrList);
    }

    private static void doTask03() {

        System.out.printf("%nTask 03 %n");

        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        for (int i = 0; i < 3; i++) appleBox1.put(new Apple());
        for (int i = 0; i < 5; i++) appleBox2.put(new Apple());
        for (int i = 0; i < 2; i++) orangeBox.put(new Orange());

        System.out.println("result of compare: " + appleBox1.compare(orangeBox));


    }

}
